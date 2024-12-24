package com.yeoljeong.autovote.domain.poll

import com.yeoljeong.autovote.domain.user.UserReader
import org.springframework.stereotype.Service

@Service
class VoteService(
    private val voteManager: VoteManager,
    private val pollReader: PollReader,
    private val userReader: UserReader
) {

    fun vote(email: String, options: Set<Long>) {
        userReader.read(email).let { user ->
            voteManager.vote(user.id, options)
        }
    }

    fun cancel(email: String, pollId: Long) {
        val user = userReader.read(email)
        val poll = pollReader.read(pollId)
        poll.options.forEach {
            user.let { user -> voteManager.cancel(user.id, it.id) }
        }
    }

}