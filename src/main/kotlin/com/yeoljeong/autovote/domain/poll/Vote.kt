package com.yeoljeong.autovote.domain.poll

import com.yeoljeong.autovote.domain.user.User

data class Vote(
    val id: Long,
    val user: User,
    val pollOption: PollOption
)