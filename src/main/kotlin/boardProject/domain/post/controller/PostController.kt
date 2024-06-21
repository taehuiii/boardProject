package boardProject.domain.post.controller

import boardProject.domain.post.dto.CreatePostRequest
import boardProject.domain.post.dto.PostResponse
import boardProject.domain.post.dto.PostsResponse
import boardProject.domain.post.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/posts")
class PostController(
    private val postService: PostService,
) {

    @GetMapping("/all")
    fun getPosts(): ResponseEntity<List<PostsResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPosts())
    }

    @GetMapping("/{postId}")
    fun getPostById(@PathVariable postId: Long): ResponseEntity<PostResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPostById(postId))
    }

    @PostMapping()
    fun createPost(
        @RequestHeader("Authorization") token: String,
        @RequestBody request: CreatePostRequest
    ): ResponseEntity<PostResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(token, request))
    }


}