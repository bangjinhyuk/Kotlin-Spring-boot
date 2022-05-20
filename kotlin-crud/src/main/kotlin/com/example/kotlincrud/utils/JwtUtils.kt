package com.example.kotlincrud.utils

import com.example.kotlincrud.model.dto.LoginResponse
import com.example.kotlincrud.model.entity.User
import com.example.kotlincrud.service.UserDetailsServiceImpl
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtils(private val userDetailsService: UserDetailsServiceImpl) {

    val EXP_TIME: Long = 1000L * 60 * 60 * 3
    val EXP_RE_TIME: Long = 1000L * 60 * 60 * 30
    val SIGNATURE_ALG: SignatureAlgorithm = SignatureAlgorithm.HS256

    @Value("\${secret}")
    lateinit var JWT_SECRET: String


    // 토큰생성
    fun createToken(user: User): LoginResponse {
        val claims: Claims = Jwts.claims().setSubject(user.id.toString())
        claims["email"] = user.email
        claims["role"] = user.role

        return LoginResponse(
            Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis()+ EXP_TIME))
            .signWith(SIGNATURE_ALG, JWT_SECRET)
            .compact(),
            Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis()+ EXP_RE_TIME))
            .signWith(SIGNATURE_ALG, JWT_SECRET)
            .compact()
        )
    }

    // 토큰검증
    fun validation(token: String) : Boolean {
        val claims: Claims = getAllClaims(token)
        val exp: Date = claims.expiration
        return exp.after(Date())
    }

    // 토큰에서 username 파싱
    fun parseEmail(token: String): String {
        val claims: Claims = getAllClaims(token)
        return claims["email"] as String
    }

    // username으로 Authentcation객체 생성
    fun getAuthentication(email: String): Authentication {
        val userDetails: UserDetails = userDetailsService.loadUserByUsername(email)
        return UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
    }


    // 모든 Claims 조회
    private fun getAllClaims(token: String): Claims {
        return Jwts.parser()
            .setSigningKey(JWT_SECRET)
            .parseClaimsJws(token)
            .body
    }
}