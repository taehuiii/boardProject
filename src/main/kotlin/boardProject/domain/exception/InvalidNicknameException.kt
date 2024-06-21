package boardProject.domain.exception

data class InvalidNicknameException(
    override val message: String? = "잘못된 닉네임 요청"
) : RuntimeException(message)
