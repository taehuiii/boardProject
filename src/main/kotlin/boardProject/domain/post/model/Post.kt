package boardProject.domain.post.model

import boardProject.domain.auth.model.Member
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

    ) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    companion object {
        fun of(title: String, content: String, nickname: String, createdAt: LocalDateTime, member: Member): Post {

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