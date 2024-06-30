package boardProject.infra.security.jwt

import boardProject.domain.auth.model.Role
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.time.Duration
import java.time.Instant
import java.util.*

@Component
class JwtPlugin(
    @Value("boardProject") private val issuer: String,
    @Value("eyJhbGciOiJIUzI1NiJ9.eyJJc3N1ZXIiOiJ0ZWFtNS5iYWNrb2ZmaWNlIiwiaWF0IjoxNzE4MjU3OTgyfQ.9JMrJy0t48PhuYJLx9KLlaAWQ9arWnSPMsedGBW0T5U") private val secret: String,
    @Value("168") private val accessTokenExpirationHour: Long,
) {

    fun validateToken(jwt: String): Result<Jws<Claims>> {
        return kotlin.runCatching {
            val key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))
            Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt)
        }
    }

    fun generateAccessToken(subject: String, email: String, role: Role): String {
        return generateToken(subject, email, role, Duration.ofHours(accessTokenExpirationHour))
    }

    private fun generateToken(subject: String, email: String, role: Role, expirationPeriod: Duration?): String {
        val claims: Claims = Jwts.claims()
            .add(mapOf("email" to email, "role" to role))
            .build()

        val key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8)) //todo
        val now = Instant.now()

        return Jwts.builder()
            .subject(subject)
            .issuer(issuer)
            .issuedAt(Date.from(now))
            .expiration(Date.from(now.plus(expirationPeriod)))
            .claims(claims)
            .signWith(key)
            .compact()
    }


}