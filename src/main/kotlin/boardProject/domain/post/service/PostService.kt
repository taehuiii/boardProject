package boardProject.domain.post.service

import boardProject.domain.auth.repository.MemberRepository
import boardProject.domain.exception.InvalidCredentialException
import boardProject.domain.exception.ModelNotFoundException
import boardProject.domain.post.dto.CreatePostRequest
import boardProject.domain.post.dto.PostResponse
import boardProject.domain.post.dto.PostsResponse
import boardProject.domain.post.model.Post
import boardProject.domain.post.repository.PostRepository
import boardProject.infra.security.jwt.JwtPlugin
import org.springframework.stereotype.Service

@Service
class PostService(
    private val postRepository: PostRepository,
    private val memberRepository: MemberRepository,
    private val jwtPlugin: JwtPlugin,

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

    fun createPost(token: String, request: CreatePostRequest): PostResponse {
        //1. 토큰 검증하고
        val result = jwtPlugin.validateToken(token)

        if (result.isFailure) {
            throw InvalidCredentialException("Invalid JWT Token")
        }
        //2. 토큰에서 작성자 정보 추출하고
        val claims = result.getOrThrow().payload //Jws<Claims>객체에서 payload 추출 -> JWT 토큰의 클레임을 포함하는 맵(Map) 객체)
        val nickname = claims["nickname"].toString()

        //3. 작성자 entity
        val member = memberRepository.findByNickname(nickname)
            ?: throw ModelNotFoundException("member", nickname)

        //4. 포스트 생성해서 저장
        val post = Post.of(
            title = request.title,
            content = request.content,
            nickname = nickname,
            createdAt = request.createdAt
        )

        postRepository.save(post)

        return PostResponse.from(post)


    }
}