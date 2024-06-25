package boardProject.domain.post.dto.comment

import boardProject.domain.post.model.Comment
import java.time.LocalDateTime

data class CommentsResponse(
    val id: Long,
    val content: String,
    val createdAt: LocalDateTime,
    val authorId: Long,

    ) {

    companion object {
        fun from(comments: List<Comment>): List<CommentsResponse> = comments.map { comment ->

            CommentsResponse(
                id = comment.id!!,
                content = comment.content,
                createdAt = comment.createdAt,
                authorId = comment.member.id!!,
            )

        }

        fun from(comment: Comment): CommentsResponse {
            return CommentsResponse(
                id = comment.id!!,
                content = comment.content,
                createdAt = comment.createdAt,
                authorId = comment.member.id!!,
            )

        }
    }
}
