package boardProject.domain.post.dto

import java.time.LocalDateTime

data class CreatePostRequest(
    val title: String,
    val nickname: String,
    val createdAt: LocalDateTime,
    val content: String,

    )
