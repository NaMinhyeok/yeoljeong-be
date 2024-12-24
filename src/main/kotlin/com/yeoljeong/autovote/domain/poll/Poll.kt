package com.yeoljeong.autovote.domain.poll

import java.time.LocalDateTime

data class Poll(
    val id: Long,
    val userId: Long,
    val title: String,
    val description: String,
    val endAt: LocalDateTime,
    val status: PollStatus,
    val options: List<PollOption>
) {

    companion object {
        fun register(
            userId: Long,
            title: String,
            description: String,
            endAt: LocalDateTime,
            options: List<String>
        ): Poll {
            return Poll(0, userId, title, description, endAt, PollStatus.OPEN, options.map { PollOption.create(it) })
        }
    }

    fun close() = this.copy(status = PollStatus.CLOSED)

    fun updateDeadline(date: LocalDateTime) = this.copy(endAt = date)

}