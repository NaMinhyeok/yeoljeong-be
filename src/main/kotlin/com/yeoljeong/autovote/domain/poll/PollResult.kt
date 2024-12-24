package com.yeoljeong.autovote.domain.poll

data class PollResult(
    val pollId: Long,
    val writer: String,
    val writerImgUrl: String,
    val title: String,
    val description: String,
    val endAt: String,
    val status: PollStatus,
    val voted: Boolean,
    val options: List<PollOptionResult>
) {
}