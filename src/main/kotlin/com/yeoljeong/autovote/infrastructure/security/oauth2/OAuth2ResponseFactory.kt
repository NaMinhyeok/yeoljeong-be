package com.yeoljeong.autovote.infrastructure.security.oauth2

import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest

interface OAuth2ResponseFactory {

    fun isSupport(userRequest: OAuth2UserRequest): Boolean

    fun create(attributes: Map<String, Any>): OAuth2Response

}