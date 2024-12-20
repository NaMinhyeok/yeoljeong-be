package com.yeoljeong.autovote.application.api.poll.request

data class VoteRequest(
    val optionIds: Set<Long>
) {
}