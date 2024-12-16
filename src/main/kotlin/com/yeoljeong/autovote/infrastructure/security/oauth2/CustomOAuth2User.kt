package com.yeoljeong.autovote.infrastructure.security.oauth2

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.core.user.OAuth2User

data class CustomOAuth2User(
    private val oAuth2Response: OAuth2Response,
    private val role: String
) : OAuth2User {
    override fun getName(): String {
        return oAuth2Response.getName()
    }

    override fun getAttributes(): MutableMap<String, Any> {
        return mutableMapOf(
            "provider" to oAuth2Response.getProvider(),
            "providerId" to oAuth2Response.getProviderId(),
            "email" to oAuth2Response.getEmail(),
            "name" to oAuth2Response.getName(),
            "imgUrl" to oAuth2Response.getImgUrl()
        )
    }

    override fun getAuthorities() = mutableListOf(SimpleGrantedAuthority(role))
}