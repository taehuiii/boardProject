package boardProject.domain.auth.dto

class LoginResponse(
    val accessToken: String,
) {

    companion object {
        fun from(token: String): LoginResponse = LoginResponse(
            accessToken = token,
        )
    }
}