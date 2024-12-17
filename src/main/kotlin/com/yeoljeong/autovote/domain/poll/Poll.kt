package com.yeoljeong.autovote.domain.poll

import com.yeoljeong.autovote.domain.user.User
import java.time.LocalDateTime

data class Poll(
    val id: Long,
    val user: User,
    val title: String,
    val description: String,
    val endAt: LocalDateTime,
    val status: PollStatus,
    val options: List<PollOption>
) {
}