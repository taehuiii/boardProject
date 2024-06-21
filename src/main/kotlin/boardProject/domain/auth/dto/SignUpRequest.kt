package boardProject.domain.auth.dto

data class SignUpRequest(
    val nickname: String,
    val password: String,
    val password_re: String,
)