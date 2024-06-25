package boardProject.domain.post.model

import boardProject.domain.auth.model.Member
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
    @Column(name = "post_id", nullable = false)
    val post: Post,

    @ManyToOne
    @Column(name = "member_id", nullable = false)
    val member: Member,

//    @Column(name = "updated_at", nullable = false)
//    var updatedAt: LocalDateTime,
//
//    @Column(name = "deleted_at")
//    var deletedAt: LocalDateTime?,

) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    fun update(newContent: String) {
        //todo : validate length
        content = newContent
        //updatedAt = LocalDateTime.now()

    }

    companion object {
        //fun validate Length
        fun of(content: String, post: Post, member: Member): Comment {

            //validate
            val timestamp = LocalDateTime.now()

            return Comment(
                content = content,
                createdAt = timestamp,
                post = post,
                member = member
            )
        }
    }
}
