package com.yeoljeong.autovote.database.poll

import com.yeoljeong.autovote.domain.poll.Poll
import com.yeoljeong.autovote.domain.poll.PollDao
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class PollRepositoryAdaptor(
    private val pollRepository: PollRepository
) : PollDao {
    override fun save(poll: Poll): Poll {
        return pollRepository.save(PollEntity.from(poll)).toDomain()
    }

    override fun findById(pollId: Long): Poll? {
        return pollRepository.findByIdOrNull(pollId)?.toDomain()
    }
}