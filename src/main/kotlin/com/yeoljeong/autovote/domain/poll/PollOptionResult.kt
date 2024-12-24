package com.yeoljeong.autovote.domain.poll

data class PollOptionResult(
    val id: Long,
    val content: String,
    val count: Int,
    val votedUserName: List<String>
) {
}