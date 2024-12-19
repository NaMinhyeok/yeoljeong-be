package com.yeoljeong.autovote.domain.user

data class User(
    val id: Long = 0,
    val name: String,
    val email: String,
    val imgUrl: String
) {
}