package boardProject.domain.post.service

import boardProject.domain.auth.model.Member
import boardProject.domain.auth.repository.MemberRepository
import boardProject.domain.exception.AlreadyDeletedException
import boardProject.domain.exception.InvalidCredentialException
import boardProject.domain.exception.ModelNotFoundException
import boardProject.domain.post.dto.post.CreatePostRequest
import boardProject.domain.post.dto.post.PostsResponse
import boardProject.domain.post.dto.post.UpdatePostRequest
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

        var posts: List<Post> = postRepository.findAllByDeletedAtIsNull() ?: throw ModelNotFoundException("Post", null)

        posts = posts.sortedByDescending { it.createdAt }
        return PostsResponse.from(posts)
    }

    fun getPostById(postId: Long): PostsResponse {
        val post = postRepository.findPostByIdOrNull(postId) ?: throw ModelNotFoundException("Post", postId)
        if (post.isDeleted()) {
            throw AlreadyDeletedException("already deleted item", postId)
        }
        return PostsResponse.from(post)
    }

    @Transactional
    fun createPost(token: String, request: CreatePostRequest): PostsResponse {

        val author = accessInfo(token)

        val post = Post.of(
            title = request.title,
            content = request.content,
            nickname = author.nickname,
            createdAt = request.createdAt,
            member = author,
        )

        postRepository.save(post)

        return PostsResponse.from(post)


    }

    @Transactional
    fun updatePost(token: String, postId: Long, request: UpdatePostRequest): PostsResponse {

        val access = accessInfo(token)
        val post = validateAuthor(postId, access)

        //todo : post.update 함수 구현
        post.title = request.title
        post.content = request.title
        //todo: dirty checking ?

        return PostsResponse.from(post)


    }

    @Transactional
    fun deletePost(token: String, postId: Long) {

        val access = accessInfo(token)
        val post = validateAuthor(postId, access)

        post.delete()

    }

    //todo : comment랑 같이 쓸 수 있게 따로 빼는건 어떨지
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

        val post =
            postRepository.findPostByIdOrNull(postId) ?: throw ModelNotFoundException("post", postId)


        if (post.nickname != accessInfo.nickname) {
            //todo : UnauthorizedAccessException
        }

        return post
    }


}