package com.yeoljeong.autovote.database.poll

import org.springframework.data.jpa.repository.JpaRepository

interface VoteRepository : JpaRepository<VoteEntity, Long> {

    fun deleteByUserIdAndOptionId(userId: Long, optionId: Long)

    fun existsByUserIdAndOptionId(userId: Long, optionId: Long): Boolean

    fun findByOptionId(optionId: Long): List<VoteEntity>
}