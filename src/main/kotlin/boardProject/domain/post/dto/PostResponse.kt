package boardProject.domain.post.dto

import boardProject.domain.post.model.Post
import java.time.LocalDateTime

data class PostResponse(
    val title: String,
    val nickname: String,
    val createdAt: LocalDateTime,
    val content: String,

    ) {
    companion object {
        fun from(post: Post): PostResponse = PostResponse(
            title = post.title,
            nickname = post.nickname,
            createdAt = post.createdAt,
            content = post.content
        )
    }
}
