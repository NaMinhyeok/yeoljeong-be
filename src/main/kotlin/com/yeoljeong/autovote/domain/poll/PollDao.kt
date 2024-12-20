package com.yeoljeong.autovote.domain.poll

interface PollDao {

    fun save(poll: Poll): Poll

    fun findById(pollId: Long): Poll?
}