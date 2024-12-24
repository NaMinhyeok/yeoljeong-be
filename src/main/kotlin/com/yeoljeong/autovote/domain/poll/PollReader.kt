package com.yeoljeong.autovote.domain.poll

import org.springframework.stereotype.Component

@Component
class PollReader(
    private val pollDao: PollDao
) {
    fun read(pollId: Long): Poll {
        return pollDao.findById(pollId) ?: throw IllegalArgumentException("투표를 찾을 수 없습니다.")
    }
}