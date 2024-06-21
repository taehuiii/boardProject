package boardProject.domain.exception

data class InvalidPasswordException(
    override val message: String? = "잘못된 비밀번호 요청"
) : RuntimeException(message)
