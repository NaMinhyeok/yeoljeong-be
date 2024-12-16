package com.yeoljeong.autovote.infrastructure.security

interface OAuth2Response {

    fun getProvider(): String

    fun getProviderId(): String

    fun getEmail(): String

    fun getName(): String

    fun getImgUrl(): String

}