package com.yeoljeong.autovote.domain.poll

import com.yeoljeong.autovote.application.api.poll.request.PollRegisterRequest
import com.yeoljeong.autovote.domain.user.UserReader
import org.springframework.stereotype.Service

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

}