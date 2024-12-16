package com.yeoljeong.autovote.database.user

import com.yeoljeong.autovote.database.BaseEntity
import com.yeoljeong.autovote.domain.user.User
import jakarta.persistence.*

@Table(name = "users")
@Entity
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    val email: String,
    val imgUrl: String
) : BaseEntity() {
    companion object {
        fun from(user: User): UserEntity {
            return UserEntity(
                name = user.name,
                email = user.email,
                imgUrl = user.imgUrl
            )
        }
    }

    fun toDomain(): User {
        return User(
            id = id,
            name = name,
            email = email,
            imgUrl = imgUrl
        )
    }

}