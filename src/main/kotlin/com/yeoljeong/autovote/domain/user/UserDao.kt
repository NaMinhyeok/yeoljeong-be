package com.yeoljeong.autovote.domain.user

interface UserDao {

    fun save(user: User): User

    fun findByEmail(email: String): User?

    fun findById(id: Long): User?

}