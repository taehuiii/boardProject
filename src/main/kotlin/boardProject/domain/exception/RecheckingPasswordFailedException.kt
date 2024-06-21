package boardProject.domain.exception

data class RecheckingPasswordFailedException(
    override val message: String? = "두 비밀번호가 일치하지 않습니다."
) : RuntimeException(message)