package boardProject.domain.exception

data class InvalidCredentialException(
    override val message: String? = "닉네임 또는 패스워드를 확인해주세요"
) : RuntimeException(message)
