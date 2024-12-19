package com.yeoljeong.autovote.domain.poll

import org.springframework.stereotype.Component

@Component
class PollAppender(
    private val pollDao: PollDao
) {

    fun append(poll: Poll): Poll {
        return pollDao.save(poll)
    }

}