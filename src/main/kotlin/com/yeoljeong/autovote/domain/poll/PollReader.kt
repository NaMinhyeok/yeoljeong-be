package com.yeoljeong.autovote.domain.poll

import org.springframework.stereotype.Component

@Component
class PollReader(
    private val pollDao: PollDao
) {
    fun read(pollId: Long): Poll? {
        return pollDao.findById(pollId)
    }
}