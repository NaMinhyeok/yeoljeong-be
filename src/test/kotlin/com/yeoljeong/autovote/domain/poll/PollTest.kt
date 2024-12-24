package com.yeoljeong.autovote.domain.poll

import com.yeoljeong.autovote.domain.user.User
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

class PollTest : FunSpec({

    test("투표 생성") {
        // given
        val tomorrow = LocalDateTime.now().plusDays(1)

        // when
        val poll = Poll.register(1, "1월 1주차 정기매치", "드림 풋살장에서 경기 진행", tomorrow, listOf("21-23시", "23-01시"))

        // then
        poll.userId shouldBe 1
        poll.title shouldBe "1월 1주차 정기매치"
        poll.description shouldBe "드림 풋살장에서 경기 진행"
        poll.endAt shouldBe tomorrow
        poll.status shouldBe PollStatus.OPEN
        poll.options.size shouldBe 2
    }

    test("투표 종료") {
        // given
        val tomorrow = LocalDateTime.now().plusDays(1)
        val poll = Poll.register(1, "1월 1주차 정기매치", "드림 풋살장에서 경기 진행", tomorrow, listOf("21-23시", "23-01시"))

        // when
        val updatedPoll = poll.close()

        // then
        updatedPoll.status shouldBe PollStatus.CLOSED
    }

    test("투표 마감 기한 변경") {
        // given
        val tomorrow = LocalDateTime.now().plusDays(1)
        val poll = Poll.register(1, "1월 1주차 정기매치", "드림 풋살장에서 경기 진행", tomorrow, listOf("21-23시", "23-01시"))

        // when
        val updatedPoll = poll.updateDeadline(tomorrow.plusDays(1))

        // then
        updatedPoll.endAt shouldBe tomorrow.plusDays(1)
    }

}) {
    companion object {
        val user = User(1, "나민혁", "nmh9097@naver.com", "https://avatars.githubusercontent.com/u/77449510?v=4")
    }
}