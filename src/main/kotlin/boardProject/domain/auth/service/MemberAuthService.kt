package boardProject.domain.auth.service

import boardProject.domain.auth.dto.LoginRequest
import boardProject.domain.auth.dto.LoginResponse
import boardProject.domain.auth.dto.SignUpRequest
import boardProject.domain.auth.dto.SignUpResponse
import boardProject.domain.auth.model.Member
import boardProject.domain.auth.repository.MemberRepository
import boardProject.domain.exception.ExistingNicknameException
import boardProject.domain.exception.InvalidCredentialException
import boardProject.domain.exception.RecheckingPasswordFailedException
import boardProject.infra.security.jwt.JwtPlugin
import org.springframework.stereotype.Service

@Service
class MemberAuthService(
    private val memberRepository: MemberRepository,
    private val jwtPlugin: JwtPlugin,
) {

    fun signUp(request: SignUpRequest): SignUpResponse {

        if (memberRepository.existsByNickname(request.nickname)) {
            throw ExistingNicknameException("중복된 닉네임입니다.")
        }

        if (request.password != request.password_re) {
            throw RecheckingPasswordFailedException("입력하신 두 비밀번호가 일치하지 않습니다.")
        }

        val member = Member.of(
            nickname = request.nickname,
            password = request.password, //todo : 암호화해서 저장
        )

        memberRepository.save(member)

        return SignUpResponse.from(member)
    }

    fun login(request: LoginRequest): LoginResponse {

        val member = memberRepository.findByNickname(request.nickname)

        if (member == null || member.password != request.password) { //TODO: PASSWORD ENCODER?
            throw InvalidCredentialException()
        }

        // jwt 생성
        return LoginResponse.from(
            jwtPlugin.generateAccessToken(
                subject = member.id.toString(),
                nickname = member.nickname
            )
        )

    }
}