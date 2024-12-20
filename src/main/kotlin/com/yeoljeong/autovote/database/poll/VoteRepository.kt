package com.yeoljeong.autovote.database.poll

import org.springframework.data.jpa.repository.JpaRepository

interface VoteRepository : JpaRepository<VoteEntity, Long> {
}