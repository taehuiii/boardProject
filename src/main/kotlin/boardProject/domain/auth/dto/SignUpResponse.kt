package boardProject.domain.auth.dto

import boardProject.domain.auth.model.Member

data class SignUpResponse(
    val nickname: String,
) {

    companion object {
        fun from(member: Member): SignUpResponse = SignUpResponse(
            nickname = member.nickname,
        )
    }
    //ex.MemberAuthService -> return MemberAuthResponse.from(member)
}
