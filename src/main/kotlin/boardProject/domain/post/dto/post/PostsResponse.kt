package boardProject.domain.post.dto.post

import boardProject.domain.auth.model.Member
import boardProject.domain.post.model.Post
import java.time.LocalDateTime

data class PostsResponse(
    val title: String,
    val nickname: String,
    val createdAt: LocalDateTime,
    val content: String,
    val member: Member,
) {
    companion object {
        fun from(posts: List<Post>): List<PostsResponse> = posts.map { post ->
            PostsResponse(
                title = post.title,
                nickname = post.nickname,
                createdAt = post.createdAt,
                content = post.content,
                member = post.member

            )
        }

        fun from(post: Post): PostsResponse = PostsResponse(
            title = post.title,
            nickname = post.nickname,
            createdAt = post.createdAt,
            content = post.content,
            member = post.member
        )


    }
}

