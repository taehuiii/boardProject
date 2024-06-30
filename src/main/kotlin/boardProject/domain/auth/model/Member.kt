package boardProject.domain.auth.model

import boardProject.domain.exception.InvalidNicknameException
import boardProject.domain.exception.InvalidPasswordException
import jakarta.persistence.*

@Entity
@Table(name = "member")
class Member(

    @Column(name = "nickname", nullable = false, unique = true)
    var nickname: String,

    @Column(name = "password", nullable = false)
    var password: String,

    @Column(name = "email", nullable = false, unique = true)
    var email: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    var role: Role

) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null


    //todo : update password

    companion object {
        //validate : rich entity

        private fun validateNickname(nickname: String) {

            if (nickname.isEmpty() || nickname.length < 3) {
                throw InvalidNicknameException(" 닉네임은 최소 3자 이상이어야합니다.")
            }

            //알파벳 대소문자, 숫자 검사
            nickname.forEach {
                if (!it.isLetterOrDigit()) {
                    throw InvalidNicknameException(" 닉네임은 알파벳 대소문자(a~z, A~Z)와 숫자(0~9)로만 구성되어야 합니다.")
                }
            }
        }

        private fun validatePassword(nickname: String, password: String) {
            if (password.length < 4) {
                throw InvalidPasswordException(" 비밀번호는 최소 4자 이상이어야합니다.")
            }

            if (password.contains(nickname, ignoreCase = false)) {
                throw InvalidPasswordException(" 비밀번호는 닉네임을 포함해선 안됩니다.")
            }
        }


        //todo : role enum으로 관리
        fun of(nickname: String, password: String, email: String, role: Role): Member {
            validateNickname(nickname)
            validatePassword(nickname, password)

            return Member(
                nickname = nickname,
                password = password,
                email = email,
                role = role,
            )

        }

        /**ex.
        val member = Member.of(
        nickname = request.nickname,
        password = request.password,
        )
         */

    }
}


