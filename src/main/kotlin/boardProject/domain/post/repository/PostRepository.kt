package boardProject.domain.post.repository

import boardProject.domain.post.model.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : JpaRepository<Post, Long> {
    override fun findAll(): List<Post>
    fun findPostById(PostId: Long): Post?
}