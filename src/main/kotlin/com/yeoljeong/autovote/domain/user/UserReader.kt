package com.yeoljeong.autovote.domain.user

import org.springframework.stereotype.Component

@Component
class UserReader(
    private val userDao: UserDao
) {

    fun findByEmail(email: String): User? {
        return userDao.findByEmail(email)
    }

}