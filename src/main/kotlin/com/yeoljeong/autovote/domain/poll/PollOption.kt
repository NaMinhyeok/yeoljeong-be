package com.yeoljeong.autovote.domain.poll

data class PollOption(
    val id: Long,
    val content: String,
) {
    companion object {
        fun create(content: String): PollOption {
            return PollOption(0, content)
        }
    }
}