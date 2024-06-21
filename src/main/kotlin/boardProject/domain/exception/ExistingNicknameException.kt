package boardProject.domain.exception

data class ExistingNicknameException(
    override val message: String? = "이미 존재하는 닉네임입니다."
) : RuntimeException(message)
