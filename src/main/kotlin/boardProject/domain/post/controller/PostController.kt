package boardProject.domain.post.controller

import boardProject.domain.post.dto.post.CreatePostRequest
import boardProject.domain.post.dto.post.PostsResponse
import boardProject.domain.post.dto.post.UpdatePostRequest
import boardProject.domain.post.service.PostService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/posts")
class PostController(
    private val postService: PostService,
) {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER')")
    fun getPosts(): ResponseEntity<List<PostsResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPosts())
    }

    @GetMapping("/{postId}")
    @PreAuthorize("hasRole('USER')")
    fun getPostById(@PathVariable postId: Long): ResponseEntity<PostsResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPostById(postId))
    }

    @PostMapping()
    @PreAuthorize("hasRole('USER')")
    fun createPost(
        @RequestHeader("Authorization") token: String,
        @RequestBody request: CreatePostRequest
    ): ResponseEntity<PostsResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(token, request))
    }

    @PutMapping("/{postId}")
    @PreAuthorize("hasRole('USER')")
    fun updatePost(
        @RequestHeader("Authorization") token: String,
        @PathVariable postId: Long,
        @RequestBody request: UpdatePostRequest
    ): ResponseEntity<PostsResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(postService.updatePost(token, postId, request))
    }

    @DeleteMapping("/{postID}")
    @PreAuthorize("hasRole('USER')")
    fun deletePost(
        @RequestHeader("Authorization") token: String,
        @PathVariable postId: Long,
    ): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT).body(postService.deletePost(token, postId))
    }


}