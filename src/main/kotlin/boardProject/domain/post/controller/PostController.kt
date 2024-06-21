package boardProject.domain.post.controller

import boardProject.domain.post.dto.PostResponse
import boardProject.domain.post.dto.PostsResponse
import boardProject.domain.post.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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

    
}