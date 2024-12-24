package com.yeoljeong.autovote.domain.poll

interface VoteDao {
    fun saveAll(votes: Iterable<Vote>): List<Vote>

    fun deleteByUserIdAndOptionId(userId: Long, optionId: Long)

    fun existByUserIdAndOptionId(userId: Long, optionId: Long): Boolean

    fun findByOptionId(optionId: Long): List<Vote>
}