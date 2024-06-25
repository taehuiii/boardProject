package boardProject.domain.post.model

import boardProject.domain.auth.model.Member
import boardProject.domain.exception.AlreadyDeletedException
import boardProject.domain.post.dto.post.UpdatePostRequest
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "post")
class Post(

    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "content", nullable = false)
    var content: String,

    @Column(name = "nickname", nullable = false)
    var nickname: String,

    @Column(name = "createdAt", nullable = false)
    var createdAt: LocalDateTime,

    @ManyToOne
    @Column(name = "member_id", nullable = false)
    val member: Member,

    //todo
//    @Column(name = "updated_at", nullable = false)
//    var updatedAt: LocalDateTime,

    @Column(name = "deleted_at")
    var deletedAt: LocalDateTime? = null,

    ) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null


    fun delete() {
        if (!isDeleted()) {
            deletedAt = LocalDateTime.now()
        } else {
            throw AlreadyDeletedException("already deleted item", id)
        }
    }

    fun update(request: UpdatePostRequest) {
        title = request.title
        content = request.content
    }

    fun isDeleted(): Boolean {
        if (deletedAt == null) {
            return false
        }
        return true
    }

    companion object {

        private fun validateTitleLength(newTitle: String) {
            if (newTitle.isEmpty() || newTitle.length > 500) {
                throw IllegalArgumentException("제목은 1자 이상, 500자 이하여야합니다.")
            }
        }

        private fun validateContentLength(newContent: String) {
            if (newContent.isEmpty() || newContent.length > 5000) {
                throw IllegalArgumentException("게시글의 내용은 1자 이상, 5000자 이하여야합니다.")
            }
        }

        fun of(title: String, content: String, nickname: String, createdAt: LocalDateTime, member: Member): Post {
            validateTitleLength(title)
            validateContentLength(content)

            return Post(
                title = title,
                content = content,
                nickname = nickname,
                createdAt = createdAt,
                member = member,
            )
        }
    }
}