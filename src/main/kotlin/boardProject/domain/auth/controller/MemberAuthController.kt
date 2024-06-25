package boardProject.domain.auth.controller

import boardProject.domain.auth.dto.LoginRequest
import boardProject.domain.auth.dto.LoginResponse
import boardProject.domain.auth.dto.SignUpRequest
import boardProject.domain.auth.dto.SignUpResponse
import boardProject.domain.auth.service.MemberAuthService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class MemberAuthController(
    private val memberAuthService: MemberAuthService,
) {
    @PostMapping("/signup")
    fun signUp(@RequestBody signUpRequest: SignUpRequest): ResponseEntity<SignUpResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(memberAuthService.signUp(signUpRequest))
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<LoginResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(memberAuthService.login(loginRequest))
    }

    @GetMapping("/check-nickname")
    fun checkNickname(@RequestParam nickname: String): ResponseEntity<Void> {
        return if (memberAuthService.isNicknameAvailable(nickname)) {
            ResponseEntity.status(HttpStatus.OK).build()
        } else {
            ResponseEntity.status(HttpStatus.CONFLICT).build()
        }
    }


}