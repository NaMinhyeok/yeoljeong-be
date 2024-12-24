package com.yeoljeong.autovote.domain.poll

import com.yeoljeong.autovote.application.api.poll.request.PollRegisterRequest
import com.yeoljeong.autovote.domain.user.UserReader
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.temporal.ChronoField

@Service
class PollService(
    private val pollAppender: PollAppender,
    private val pollReader: PollReader,
    private val voteManager: VoteManager,
    private val voteReader: VoteReader,
    private val userReader: UserReader
) {

    fun register(email: String, request: PollRegisterRequest) {
        val user = userReader.read(email)
        val poll = Poll.register(
            title = request.title,
            description = request.description,
            endAt = request.endAt,
            options = request.pollOptionContents,
            userId = user.id
        )
        pollAppender.append(poll)
    }

    fun getPoll(email: String, pollId: Long): PollResult {
        val user = userReader.read(email)
        val poll = pollReader.read(pollId)
        val writer = userReader.read(poll.userId)

        val votedOptionUser = poll.options.associateBy(
            keySelector = { it.id },
            valueTransform = { option ->
                val votes = voteReader.read(option.id)
                votes.map { vote ->
                    val candidateUser =
                        userReader.read(vote.userId)
                    candidateUser.name
                }
            }
        )
        val isVoted = poll.options.any() {
            voteManager.exist(user.id, it.id)
        }
        val response = PollResult(
            pollId = poll.id,
            writer = writer.name,
            writerImgUrl = writer.imgUrl,
            title = poll.title,
            description = poll.description,
            endAt = poll.endAt.toString(),
            status = poll.status,
            voted = isVoted,
            options = poll.options.map {
                PollOptionResult(
                    id = it.id,
                    content = it.content,
                    count = votedOptionUser[it.id]?.size ?: 0,
                    votedUserName = votedOptionUser[it.id] ?: emptyList()
                )
            }
        )
        return response
    }

    @Scheduled(cron = "0 0 9 * * SAT")
    fun bookPoll() {
        val now = LocalDateTime.now()
        val weekOfMonth = now.get(ChronoField.ALIGNED_WEEK_OF_MONTH)
        val month = now.monthValue
        val title = "${month}월 ${weekOfMonth}주차 투표"

        val endAt = now.plusDays(3)
            .withHour(12)
            .withMinute(0)
            .withSecond(0)

        // 옵션 생성
        val friday = now.plusDays(6)  // 다음주 금요일
        val saturday = now.plusDays(7)  // 다음주 토요일

        val options = listOf(
            "${friday.monthValue}/${friday.dayOfMonth} 금요일 20시~22시",
            "${friday.monthValue}/${friday.dayOfMonth} 금요일 21시~23시",
            "${friday.monthValue}/${friday.dayOfMonth} 금요일 22시~24시",
            "${saturday.monthValue}/${saturday.dayOfMonth} 토요일 20시~22시",
            "${saturday.monthValue}/${saturday.dayOfMonth} 토요일 21시~23시",
            "${saturday.monthValue}/${saturday.dayOfMonth} 토요일 22시~24시",
            "불참"
        )

        val poll = Poll.register(
            title = title,
            description = "정기 풋살 투표",
            endAt = endAt,
            options = options,
            userId = 1
        )

        pollAppender.append(poll)

    }

}