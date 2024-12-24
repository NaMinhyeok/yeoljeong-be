package com.yeoljeong.autovote.domain.poll

data class Vote(
    val id: Long,
    val userId: Long,
    val optionId: Long
)