package boardProject.domain.post.service

import boardProject.domain.auth.model.Member
import boardProject.domain.exception.ModelNotFoundException
import boardProject.domain.post.dto.comment.CommentRequest
import boardProject.domain.post.dto.comment.CommentsResponse
import boardProject.domain.post.model.Comment
import boardProject.domain.post.repository.CommentRepository
import boardProject.domain.post.repository.PostRepository
import org.springframework.stereotype.Service


@Service
class CommentService(
    private val postRepository: PostRepository,
    private val commentRepository: CommentRepository,
    private val postService: PostService,

    ) {

    fun getComments(postId: Long): List<CommentsResponse> {
        val post = postRepository.findPostByIdOrNull(postId) ?: throw ModelNotFoundException("post", postId)
        val comments =
            commentRepository.findCommentByPostIdOrNull(postId) ?: throw ModelNotFoundException("comment", postId)

        return CommentsResponse.from(comments)
    }

    fun addComment(token: String, postId: Long, request: CommentRequest): CommentsResponse {
        //validate token
        val author = postService.accessInfo(token)

        //post 찾기
        val post = postRepository.findPostByIdOrNull(postId) ?: throw ModelNotFoundException("Post", postId)

        //댓글 생성
        val comment = Comment.of(
            content = request.content,
            post = post,
            member = author
        )
        return CommentsResponse.from(comment)
    }

    fun updateComment(token: String, postId: Long, commentId: Long, request: CommentRequest): CommentsResponse {
        //todo : post delete여부 체크

        val comment = validateAuthor(commentId, postService.accessInfo(token))

        comment.update(request.content)

        return CommentsResponse.from(comment)

    }

    fun deleteComment(token: String, postId: Long, commentId: Long) {
        //todo : post delete여부 체크

        val comment = validateAuthor(commentId, postService.accessInfo(token))

        //todo : softDelete
        commentRepository.delete(comment)
    }

    fun validateAuthor(commentId: Long, accessInfo: Member): Comment {
        val comment = commentRepository.findCommentByCommentIdOrNull(commentId) ?: throw ModelNotFoundException(
            "comment",
            commentId
        )

        if (accessInfo != comment.member) {
            //todo : UnauthorizedAccessException
        }

        return comment
    }


}