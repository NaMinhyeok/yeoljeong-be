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
    private val userReader: UserReader
) {

    fun register(email: String, request: PollRegisterRequest) {
        val user = userReader.findByEmail(email) ?: throw IllegalArgumentException("사용자를 찾을 수 없습니다.")
        val poll = Poll.register(
            title = request.title,
            description = request.description,
            endAt = request.endAt,
            options = request.pollOptionContents,
            userId = user.id
        )
        pollAppender.append(poll)
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