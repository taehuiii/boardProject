package boardProject.domain.post.service

import boardProject.domain.auth.model.Member
import boardProject.domain.auth.repository.MemberRepository
import boardProject.domain.exception.InvalidCredentialException
import boardProject.domain.exception.ModelNotFoundException
import boardProject.domain.post.dto.CreatePostRequest
import boardProject.domain.post.dto.PostResponse
import boardProject.domain.post.dto.PostsResponse
import boardProject.domain.post.dto.UpdatePostRequest
import boardProject.domain.post.model.Post
import boardProject.domain.post.repository.PostRepository
import boardProject.infra.security.jwt.JwtPlugin
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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

    @Transactional
    fun createPost(token: String, request: CreatePostRequest): PostResponse {

        val author = accessInfo(token)


        //4. 포스트 생성해서 저장
        val post = Post.of(
            title = request.title,
            content = request.content,
            nickname = author.nickname,
            createdAt = request.createdAt,
            member = author,
        )

        postRepository.save(post)

        return PostResponse.from(post)


    }

    @Transactional
    fun updatePost(token: String, postId: Long, request: UpdatePostRequest): PostResponse {

        val access = accessInfo(token)
        val post = validateAuthor(postId, access)

        //todo : post.update 함수 구현
        post.title = request.title
        post.content = request.title
        //todo: dirty checking ?

        return PostResponse.from(post)


    }

    @Transactional
    fun deletePost(token: String, postId: Long) {

        //todo :  soft delete ?
        //todo : findPostByIdAndDeletedAtIsNull

        val access = accessInfo(token)
        val post = validateAuthor(postId, access)

        postRepository.delete(post)

    }

    fun accessInfo(token: String): Member {
        //1. 토큰 검증하고
        val result = jwtPlugin.validateToken(token)

        if (result.isFailure) {
            throw InvalidCredentialException("Invalid JWT Token")
        }

        //2. 토큰에서 정보 추출하고
        val claims = result.getOrThrow().payload //Jws<Claims>객체에서 payload 추출 -> JWT 토큰의 클레임을 포함하는 맵(Map) 객체)
        val nickname = claims["nickname"].toString()


        //3. 접근member entity
        val member = memberRepository.findByNickname(nickname)
            ?: throw ModelNotFoundException("member", nickname)

        return member
    }

    fun validateAuthor(postId: Long, accessInfo: Member): Post {

        //todo: findByIdOrNull / findPostByIdAndDeletedAtIsNull
        val post = postRepository.findPostById(postId) ?: throw ModelNotFoundException("post", postId) //todo : ornull?
        if (post.nickname == accessInfo.nickname) {
            return post
        } else {
            //todo : UnauthorizedAccessException
        }

        //todo : return 수정
        return post
    }

}