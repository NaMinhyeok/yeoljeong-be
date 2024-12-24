package com.yeoljeong.autovote.database.poll

import org.springframework.data.jpa.repository.JpaRepository

interface PollRepository : JpaRepository<PollEntity, Long> {
}