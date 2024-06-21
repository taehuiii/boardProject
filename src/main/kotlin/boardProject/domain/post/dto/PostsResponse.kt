package boardProject.domain.post.dto

import boardProject.domain.post.model.Post
import java.time.LocalDateTime

data class PostsResponse(
    val title: String,
    val nickname: String,
    val createdAt: LocalDateTime,
) {
    companion object {
        fun from(posts: List<Post>): List<PostsResponse> = posts.map { post ->
            PostsResponse(
                title = post.title,
                nickname = post.nickname,
                createdAt = post.createdAt,
            )
        }

   
    }
}

