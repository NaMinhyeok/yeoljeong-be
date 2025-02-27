package com.yeoljeong.autovote.application.api.poll

import com.yeoljeong.autovote.application.api.poll.request.VoteRequest
import com.yeoljeong.autovote.domain.poll.VoteService
import com.yeoljeong.autovote.infrastructure.security.oauth2.CustomOAuth2User
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1/poll/vote")
@RestController
class VoteApi(
    private val voteService: VoteService
) {
    @PostMapping
    fun vote(
        @AuthenticationPrincipal principal: CustomOAuth2User,
        @RequestBody request: VoteRequest
    ) {
        voteService.vote(principal.getEmail(), request.optionIds)
    }

    @PostMapping("/{pollId}")
    fun cancel(
        @AuthenticationPrincipal principal: CustomOAuth2User,
        @PathVariable pollId: Long
    ) {
        voteService.cancel(principal.getEmail(), pollId)
    }

}