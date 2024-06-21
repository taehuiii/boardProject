package boardProject.domain.auth.controller

import boardProject.domain.auth.dto.LoginRequest
import boardProject.domain.auth.dto.LoginResponse
import boardProject.domain.auth.dto.SignUpRequest
import boardProject.domain.auth.dto.SignUpResponse
import boardProject.domain.auth.service.MemberAuthService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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


}