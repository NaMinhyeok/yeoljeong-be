package com.yeoljeong.autovote.infrastructure.security.oauth2

import com.yeoljeong.autovote.domain.user.User

interface OAuth2Response {

    fun getProvider(): String

    fun getProviderId(): String

    fun getEmail(): String

    fun getName(): String

    fun getImgUrl(): String

    fun toUser(): User

}