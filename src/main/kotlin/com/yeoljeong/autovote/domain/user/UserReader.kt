package com.yeoljeong.autovote.domain.user

import org.springframework.stereotype.Component

@Component
class UserReader(
    private val userDao: UserDao
) {

    fun read(email: String): User? {
        return userDao.findByEmail(email)
    }

    fun read(id: Long): User? {
        return userDao.findById(id)
    }

}