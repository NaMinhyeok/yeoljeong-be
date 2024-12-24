package com.yeoljeong.autovote.infrastructure.security.oauth2

import com.yeoljeong.autovote.domain.user.User
import com.yeoljeong.autovote.domain.user.UserAppender
import com.yeoljeong.autovote.domain.user.UserReader
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class CustomOAuth2UserService(
    private val oauth2Responses: List<OAuth2ResponseFactory>,
    private val userAppender: UserAppender,
    private val userReader: UserReader
) : DefaultOAuth2UserService() {

    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val user = super.loadUser(userRequest)

        val oAuth2Response = (
                oauth2Responses
                    .find { it.isSupport(userRequest) }
                    ?.create(user.attributes)
                    ?: throw IllegalArgumentException("지원하지 않는 OAuth2 인증입니다.")
                )
        handleUser(oAuth2Response.toUser())
        return CustomOAuth2User(oAuth2Response, "ROLE_USER")
    }

    private fun handleUser(user: User) {
        // TODO: 사용자가 로그인 할 때 변경된 정보가 있으면 변경할 수 있도록 구현
        userReader.read(user.email)
            ?: userAppender.append(user)
    }
}