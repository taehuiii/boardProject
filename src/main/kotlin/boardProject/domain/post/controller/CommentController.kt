package boardProject.domain.post.controller

import boardProject.domain.post.dto.comment.CommentRequest
import boardProject.domain.post.dto.comment.CommentsResponse
import boardProject.domain.post.service.CommentService
import io.swagger.v3.oas.annotations.parameters.RequestBody
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/posts/{postId}/comments")
class CommentController(
    private val commentService: CommentService,
) {
    @GetMapping()
    fun getComments(@PathVariable postId: Long): ResponseEntity<List<CommentsResponse>> {

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.getComments(postId))
    }

    @PostMapping
    fun addComments(
        @RequestHeader("Authorization") token: String,
        @PathVariable postId: Long,
        @RequestBody addCommentRequest: CommentRequest,
    ): ResponseEntity<CommentsResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.addComment(token, postId, addCommentRequest))
    }

    @PutMapping("/{commentId}")
    fun updateComments(
        @RequestHeader("Authorization") token: String,
        @PathVariable postId: Long,
        @PathVariable commentId: Long,
        @RequestBody updateCommentRequest: CommentRequest,
    ): ResponseEntity<CommentsResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.updateComment(token, postId, commentId, updateCommentRequest))
    }

    @DeleteMapping("/{commentId}")
    fun deleteComment(
        @RequestHeader("Authorization") token: String,
        @PathVariable postId: Long,
        @PathVariable commentId: Long,
    ): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(commentService.deleteComment(token, postId, commentId))
    }


}