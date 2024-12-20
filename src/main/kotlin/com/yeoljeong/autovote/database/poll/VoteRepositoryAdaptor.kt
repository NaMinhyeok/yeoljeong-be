package com.yeoljeong.autovote.database.poll

import com.yeoljeong.autovote.domain.poll.Vote
import com.yeoljeong.autovote.domain.poll.VoteDao
import org.springframework.stereotype.Repository

@Repository
class VoteRepositoryAdaptor(
    private val voteRepository: VoteRepository
) : VoteDao {
    override fun saveAll(votes: Iterable<Vote>): List<Vote> {
        return voteRepository.saveAll(votes.map { VoteEntity.from(it) }).map { it.toDomain() }
    }

    override fun deleteByUserIdAndOptionId(userId: Long, optionId: Long) {
        voteRepository.deleteByUserIdAndOptionId(userId, optionId)
    }

}