package com.yeoljeong.autovote.domain.poll

interface PollDao {

    fun save(poll: Poll): Poll

}