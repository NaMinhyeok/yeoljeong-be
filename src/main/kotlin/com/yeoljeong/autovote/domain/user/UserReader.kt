package com.yeoljeong.autovote.domain.user

import org.springframework.stereotype.Component

@Component
class UserReader(
    private val userDao: UserDao
) {

    fun read(email: String): User {
        return userDao.findByEmail(email) ?: throw IllegalArgumentException("사용자를 찾을 수 없습니다.")
    }

    fun read(id: Long): User {
        return userDao.findById(id) ?: throw IllegalArgumentException("사용자를 찾을 수 없습니다.")
    }

}