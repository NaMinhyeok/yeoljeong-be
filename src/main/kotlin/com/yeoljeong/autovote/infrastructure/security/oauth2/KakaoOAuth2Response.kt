package com.yeoljeong.autovote.infrastructure.security.oauth2

import com.yeoljeong.autovote.domain.user.User

data class KakaoOAuth2Response(
    private val attributes: Map<String, Any>
) : OAuth2Response {

    override fun getProvider(): String {
        return "kakao"
    }

    override fun getProviderId(): String {
        return attributes["id"].toString()
    }

    override fun getEmail(): String {
        return attributes["kakao_account"]?.let { account ->
            (account as Map<String, Any>)["email"] as? String
        } ?: throw IllegalStateException("이메일 정보를 찾을 수 없습니다.")
    }

    override fun getName(): String {
        return ((attributes["properties"] as Map<String, Any>)["nickname"]).toString()
    }

    override fun getImgUrl(): String {
        return ((attributes["properties"] as Map<String, Any>)["profile_image"]).toString()
    }

    override fun toUser(): User {
        return User(
            email = getEmail(),
            name = getName(),
            imgUrl = getImgUrl()
        )
    }
}