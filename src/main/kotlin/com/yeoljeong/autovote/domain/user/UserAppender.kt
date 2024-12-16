package com.yeoljeong.autovote.domain.user

import org.springframework.stereotype.Component

@Component
class UserAppender(
    private val userDao: UserDao
) {

    fun append(user: User): User {
        return userDao.save(user)
    }

}