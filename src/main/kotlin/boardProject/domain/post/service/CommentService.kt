package boardProject.domain.post.service

import ModelNotFoundException
import boardProject.domain.auth.model.Member
import boardProject.domain.exception.AlreadyDeletedException
import boardProject.domain.exception.UnauthorizedAccessException
import boardProject.domain.post.dto.comment.CommentRequest
import boardProject.domain.post.dto.comment.CommentsResponse
import boardProject.domain.post.model.Comment
import boardProject.domain.post.model.Post
import boardProject.domain.post.repository.CommentRepository
import boardProject.domain.post.repository.PostRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class CommentService(
    private val postRepository: PostRepository,
    private val commentRepository: CommentRepository,
    private val postService: PostService,

    ) {

    fun getComments(postId: Long): List<CommentsResponse> {
        checkPostDeletedAndReturn(postId)

        val comments =
            commentRepository.findByPost_Id(postId) ?: throw ModelNotFoundException("comment", postId)

        return CommentsResponse.from(comments)
    }

    @Transactional
    fun addComment(token: String, postId: Long, request: CommentRequest): CommentsResponse {
        //validate token
        val author = postService.accessInfo(token)

        //post 찾기
        val post = checkPostDeletedAndReturn(postId)

        //댓글 생성
        val comment = Comment.of(
            content = request.content,
            post = post,
            member = author
        )
        return CommentsResponse.from(comment)
    }

    @Transactional
    fun updateComment(token: String, postId: Long, commentId: Long, request: CommentRequest): CommentsResponse {

        checkPostDeletedAndReturn(postId)


        val comment = validateAuthor(commentId, postService.accessInfo(token))
        comment.update(request.content)


        return CommentsResponse.from(comment)

    }

    @Transactional
    fun deleteComment(token: String, postId: Long, commentId: Long) {
        checkPostDeletedAndReturn(postId)

        val comment = validateAuthor(commentId, postService.accessInfo(token))
        comment.delete()

    }

    fun validateAuthor(commentId: Long, accessInfo: Member): Comment {
        val comment = commentRepository.findById(commentId).orElseThrow {
            ModelNotFoundException(
                "comment",
                commentId
            )
        }

        if (accessInfo != comment.member) {
            throw UnauthorizedAccessException(itemId = commentId, memberId = accessInfo.id)
        }

        return comment
    }

    fun checkPostDeletedAndReturn(postId: Long): Post {
        val post = postRepository.findById(postId).orElseThrow { ModelNotFoundException("Post", postId) }
        if (post.isDeleted()) {
            throw AlreadyDeletedException("post is already deleted", itemId = postId)
        }
        return post
    }


}