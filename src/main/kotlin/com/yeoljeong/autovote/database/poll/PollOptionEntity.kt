package com.yeoljeong.autovote.database.poll

import com.yeoljeong.autovote.domain.poll.PollOption
import jakarta.persistence.*

@Table(name = "poll_option")
@Entity
class PollOptionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val content: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poll_id")
    val poll: PollEntity
) {
    fun toDomain(): PollOption {
        return PollOption(id, content)
    }

    companion object {
        fun from(option: PollOption, poll: PollEntity): PollOptionEntity {
            return PollOptionEntity(option.id, option.content, poll)
        }
    }
}