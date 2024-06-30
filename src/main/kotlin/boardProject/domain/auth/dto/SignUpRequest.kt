package boardProject.domain.auth.dto

import boardProject.domain.auth.model.Role

data class SignUpRequest(
    val nickname: String,
    val password: String,
    val password_re: String,
    val email: String,
    val role: Role,
)