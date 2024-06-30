package boardProject.domain.auth.dto

data class LoginRequest(
    val nickname: String,
    val password: String,
    //val email: String,
    //val role: Role,
)
