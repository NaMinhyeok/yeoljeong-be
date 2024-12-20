package com.yeoljeong.autovote.database.poll

import com.yeoljeong.autovote.database.BaseEntity
import com.yeoljeong.autovote.domain.poll.Vote
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class VoteEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val userId: Long,
    val optionId: Long
) : BaseEntity() {
    companion object {
        fun from(vote: Vote): VoteEntity {
            return VoteEntity(
                id = vote.id,
                userId = vote.userId,
                optionId = vote.optionId
            )
        }
    }

    fun toDomain(): Vote {
        return Vote(
            id = id,
            userId = userId,
            optionId = optionId
        )
    }

}