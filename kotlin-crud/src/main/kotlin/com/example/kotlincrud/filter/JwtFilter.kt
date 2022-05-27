package com.example.kotlincrud.filter

import com.example.kotlincrud.model.repository.UserRepository
import com.example.kotlincrud.utils.JwtUtils
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtFilter(
    private val jwtUtils: JwtUtils,
    private val userRepository: UserRepository
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        // 헤더에 Authorization이 있다면 가져온다.
        val authorizationHeader: String? = request.getHeader("Authorization") ?: return filterChain.doFilter(request, response)
        // Bearer타입 토큰이 있을 때 가져온다.
        val token = authorizationHeader?.substring("Bearer ".length) ?: return filterChain.doFilter(request, response)

        // 토큰 검증
        if (jwtUtils.validation(token)) {
            // 토큰에서 username 파싱
            val username = jwtUtils.parseEmail(token)
            // username으로 AuthenticationToken 생성
            val authentication: Authentication = jwtUtils.getAuthentication(username)
            // 생성된 AuthenticationToken을 SecurityContext가 관리하도록 설정
            SecurityContextHolder.getContext().authentication = authentication
            request.setAttribute("user", userRepository.findByEmail((authentication.principal as UserDetails).username).get())
        }

        filterChain.doFilter(request, response)
    }
}