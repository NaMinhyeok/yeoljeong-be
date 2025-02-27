package com.yeoljeong.autovote.application.api.poll

import com.yeoljeong.autovote.application.api.poll.request.PollRegisterRequest
import com.yeoljeong.autovote.application.support.response.ApiResponse
import com.yeoljeong.autovote.domain.poll.PollService
import com.yeoljeong.autovote.infrastructure.security.oauth2.CustomOAuth2User
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1/poll")
@RestController
class PollApi(
    private val pollService: PollService
) {
    @PostMapping
    fun register(
        @AuthenticationPrincipal principal: CustomOAuth2User,
        @RequestBody request: PollRegisterRequest
    ) {
        pollService.register(principal.getEmail(), request)
    }

    @GetMapping("/{pollId}")
    fun getPoll(
        @AuthenticationPrincipal principal: CustomOAuth2User,
        @PathVariable pollId: Long
    ) = ApiResponse.success(
        pollService.getPoll(principal.getEmail(), pollId)
    )


}