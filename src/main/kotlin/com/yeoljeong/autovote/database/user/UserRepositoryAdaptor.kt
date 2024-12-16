package com.yeoljeong.autovote.database.user

import com.yeoljeong.autovote.domain.user.User
import com.yeoljeong.autovote.domain.user.UserDao
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryAdaptor(
    private val userRepository: UserRepository
) : UserDao {

    override fun save(user: User): User {
        return userRepository.save(UserEntity.from(user)).toDomain()
    }

    override fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)?.toDomain()
    }

}