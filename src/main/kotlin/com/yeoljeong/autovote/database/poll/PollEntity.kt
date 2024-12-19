package com.yeoljeong.autovote.database.poll

import com.yeoljeong.autovote.database.BaseEntity
import com.yeoljeong.autovote.domain.poll.Poll
import com.yeoljeong.autovote.domain.poll.PollStatus
import jakarta.persistence.*
import java.time.LocalDateTime

@Table(name = "polls")
@Entity
class PollEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val userId: Long,
    val title: String,
    val description: String,
    val endAt: LocalDateTime,
    val status: PollStatus,
    @OneToMany(mappedBy = "poll", cascade = [CascadeType.ALL], orphanRemoval = true)
    val options: MutableList<PollOptionEntity> = mutableListOf()
) : BaseEntity() {

    fun toDomain(): Poll {
        return Poll(id, userId, title, description, endAt, status, options.map { it.toDomain() })
    }

    companion object {
        fun from(poll: Poll): PollEntity {
            return PollEntity(
                poll.id,
                poll.userId,
                poll.title,
                poll.description,
                poll.endAt,
                poll.status,
            ).apply { options.addAll(poll.options.map { PollOptionEntity.from(it, this) }) }
        }
    }

}