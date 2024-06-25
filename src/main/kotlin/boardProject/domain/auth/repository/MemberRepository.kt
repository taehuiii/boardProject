package boardProject.domain.auth.repository

import boardProject.domain.auth.model.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository : JpaRepository<Member, Long> {
    fun existsByNickname(nickname: String): Boolean
    fun findByNickname(nickname: String): Member?

}