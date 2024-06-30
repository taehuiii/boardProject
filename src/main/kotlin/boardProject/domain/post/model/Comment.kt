package boardProject.domain.post.model

import boardProject.domain.auth.model.Member
import boardProject.domain.exception.AlreadyDeletedException
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "comment")
class Comment(

    @Column(name = "content", nullable = false)
    var content: String,

    @Column(name = "createdAt", nullable = false)
    var createdAt: LocalDateTime,

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    val post: Post,

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    val member: Member,

    @Column(name = "updated_at", nullable = true)
    var updatedAt: LocalDateTime? = null,

    @Column(name = "deleted_at")
    var deletedAt: LocalDateTime? = null,

    ) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    fun update(newContent: String) {
        validateCommentLength(content)

        if (!isDeleted()) {
            content = newContent
            updatedAt = LocalDateTime.now()
        } else {
            throw AlreadyDeletedException("already deleted item", id)
        }


    }

    fun delete() {
        if (!isDeleted()) {
            deletedAt = LocalDateTime.now()
        } else {
            throw AlreadyDeletedException("already deleted item", id)
        }
    }

    fun isDeleted(): Boolean {
        if (deletedAt == null) {
            return false
        }
        return true
    }

    companion object {

        private fun validateCommentLength(newContent: String) {
            if (newContent.isEmpty() || newContent.length > 1000) {
                throw IllegalArgumentException("댓글의 내용은 1자 이상, 1000자 이하여야합니다.")
            }
        }

        fun of(content: String, post: Post, member: Member): Comment {

            validateCommentLength(content)

            val timestamp = LocalDateTime.now()

            return Comment(
                content = content,
                createdAt = timestamp,
                post = post,
                member = member,
            )
        }
    }
}
