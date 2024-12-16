package com.yeoljeong.autovote.infrastructure.security.oauth2

import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.stereotype.Component

@Component
class KakaoOAuth2ResponseFactory : OAuth2ResponseFactory {

    override fun isSupport(userRequest: OAuth2UserRequest): Boolean {
        return userRequest.clientRegistration.registrationId == "kakao"
    }

    override fun create(attributes: Map<String, Any>): OAuth2Response {
        return KakaoOAuth2Response(attributes)
    }

}