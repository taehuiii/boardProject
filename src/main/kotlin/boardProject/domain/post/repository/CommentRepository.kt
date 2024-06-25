package boardProject.domain.post.repository

import boardProject.domain.post.model.Comment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository : JpaRepository<Comment, Long> {

    fun findCommentByPostIdOrNull(postId: Long): List<Comment>?
    fun findCommentByCommentIdOrNull(commentId: Long): Comment?
}