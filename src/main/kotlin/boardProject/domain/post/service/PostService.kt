package boardProject.domain.post.service

import boardProject.domain.exception.ModelNotFoundException
import boardProject.domain.post.dto.PostResponse
import boardProject.domain.post.dto.PostsResponse
import boardProject.domain.post.model.Post
import boardProject.domain.post.repository.PostRepository
import org.springframework.stereotype.Service

@Service
class PostService(
    private val postRepository: PostRepository,

    ) {

    fun getPosts(): List<PostsResponse> {
        var posts: List<Post> = postRepository.findAll() //todo?: throw ModelNotFoundException("Post, postId")
        posts = posts.sortedByDescending { it.createdAt }
        return PostsResponse.from(posts)
    }

    fun getPostById(postId: Long): PostResponse {
        val post = postRepository.findPostById(postId) ?: throw ModelNotFoundException("Post", postId)
        return PostResponse.from(post)
    }
}