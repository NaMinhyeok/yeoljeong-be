package com.yeoljeong.autovote.domain.poll

import com.yeoljeong.autovote.domain.user.UserReader
import org.springframework.stereotype.Service

@Service
class VoteService(
    private val voteManager: VoteManager,
    private val userReader: UserReader
) {

    fun vote(email: String, options: Set<Long>) {
        userReader.findByEmail(email)?.let { user ->
            voteManager.vote(user.id, options)
        }
    }

}