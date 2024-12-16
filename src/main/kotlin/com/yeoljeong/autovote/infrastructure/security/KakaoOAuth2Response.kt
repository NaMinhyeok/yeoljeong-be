package com.yeoljeong.autovote.infrastructure.security

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
        return (attributes["kakao_account"] as Map<String, Any>)["value"].let { value ->
            (value as Map<String, Any>)["email"].toString()
        }
    }

    override fun getName(): String {
        return ((attributes["properties"] as Map<String, Any>)["nickname"]).toString()
    }

    override fun getImgUrl(): String {
        return ((attributes["properties"] as Map<String, Any>)["profile_image_url"]).toString()
    }
}