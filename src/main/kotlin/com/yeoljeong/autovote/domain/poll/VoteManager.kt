package com.yeoljeong.autovote.domain.poll

import org.springframework.stereotype.Component

@Component
class VoteManager(
    private val voteDao: VoteDao
) {

    fun vote(userId: Long, options: Set<Long>) {
        voteDao.saveAll(options.map { Vote(id = 0, userId = 1, optionId = it) })
    }

    fun cancel(userId: Long, optionId: Long) {
        voteDao.deleteByUserIdAndOptionId(userId, optionId)
    }

    fun exist(userId: Long, optionId: Long): Boolean {
        return voteDao.existByUserIdAndOptionId(userId, optionId)
    }

}