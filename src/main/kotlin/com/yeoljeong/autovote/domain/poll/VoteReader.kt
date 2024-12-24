package com.yeoljeong.autovote.domain.poll

import org.springframework.stereotype.Component

@Component
class VoteReader(
    private val voteDao: VoteDao
) {
    fun read(optionId: Long): List<Vote> {
        return voteDao.findByOptionId(optionId)
    }
}