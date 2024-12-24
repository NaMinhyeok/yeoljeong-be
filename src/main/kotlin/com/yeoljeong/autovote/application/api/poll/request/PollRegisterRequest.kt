package com.yeoljeong.autovote.application.api.poll.request

import java.time.LocalDateTime

data class PollRegisterRequest(
    val title: String,
    val description: String,
    val endAt: LocalDateTime,
    val pollOptionContents: List<String>,
)
