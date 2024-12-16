package com.yeoljeong.autovote.infrastructure.security

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class CustomOAuth2UserService(
    private val oauth2Responses : List<OAuth2ResponseFactory>
) : DefaultOAuth2UserService() {

    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val user= super.loadUser(userRequest)

        return oauth2Responses
            .find { it.isSupport(userRequest) }
            ?.create(user.attributes)
            ?.let { CustomOAuth2User(it, "ROLE_USER") }
            ?: throw IllegalArgumentException("지원하지 않는 OAuth2 인증입니다.")
    }
}