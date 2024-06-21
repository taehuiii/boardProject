package boardProject.domain.post.model

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

    ) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    companion object {
        fun of(title: String, content: String, nickname: String, createdAt: LocalDateTime): Post {

            return Post(
                title = title,
                content = content,
                nickname = nickname,
                createdAt = createdAt,
            )
        }
    }
}