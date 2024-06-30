package boardProject.infra.security.jwt

import boardProject.domain.auth.model.Role
import boardProject.infra.security.UserPrincipal
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtPlugin: JwtPlugin
) : OncePerRequestFilter() {

    //http 요청으로부터 jwt 토큰 검증 및 인증처리

    companion object {
        private val BEARER_PATTERN = Regex("^Bearer (.+?)$")
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jwt = request.getBearerToken() //1. 헤더에서 jwt 추출

        if (jwt != null) {
            jwtPlugin.validateToken(jwt) // 2. jwt 검증
                .onSuccess {//3. jwt로 부터 정보 획득
                    val userId = it.payload.subject.toLong()
                    val role = Role.valueOf(it.payload.get("role", String::class.java))
                    val email = it.payload.get("email", String::class.java)
                    val nickname = it.payload.get("nickname", String::class.java)


                    //4-1. auth 객체에 넣은 Principal 생성
                    val principal = UserPrincipal(
                        id = userId,
                        email = email,
                        roles = setOf(role),  // collection이어서
                    )

                    //4-2.auth객체 생성
                    val authentication = JwtAuthenticationToken(
                        principal = principal,
                        details = WebAuthenticationDetailsSource().buildDetails(request)  // request로 부터 요청 상세정보 생성
                    )

                    //4-3.SecurityContext에 authentication 객체 저장
                    SecurityContextHolder.getContext().authentication = authentication
                }
        }

        //5. FilterChain 계속 진행
        filterChain.doFilter(request, response)
    }

    private fun HttpServletRequest.getBearerToken(): String? {
        //헤더 -> key AUTHORIZATION의 value 가져오기
        val headerValue = this.getHeader(HttpHeaders.AUTHORIZATION) ?: return null
        return BEARER_PATTERN.find(headerValue)?.groupValues?.get(1)//jwt 추출
    }
}