package com.yeoljeong.autovote.infrastructure.security.config

import com.yeoljeong.autovote.infrastructure.security.CustomOAuth2UserService
import com.yeoljeong.autovote.infrastructure.security.OAuth2LoginFailureHandler
import com.yeoljeong.autovote.infrastructure.security.OAuth2LoginSuccessHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@EnableWebSecurity
@Configuration
class SecurityConfig(
    private val customOAuth2UserService: CustomOAuth2UserService,
    private val oAuth2LoginSuccessHandler: OAuth2LoginSuccessHandler,
    private val oAuth2LoginFailureHandler: OAuth2LoginFailureHandler
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .httpBasic { it.disable() }
            .cors { it.disable() }
            .authorizeHttpRequests {
                it.anyRequest().permitAll()
            }
            .oauth2Login { oauth2 ->
                oauth2.userInfoEndpoint {
                    it.userService(customOAuth2UserService)
                }
                oauth2.successHandler(oAuth2LoginSuccessHandler)
                oauth2.failureHandler(oAuth2LoginFailureHandler)
            }
            .build()
    }
}