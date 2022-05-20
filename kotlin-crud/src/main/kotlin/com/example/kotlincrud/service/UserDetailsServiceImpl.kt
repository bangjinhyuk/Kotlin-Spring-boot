package com.example.kotlincrud.service

import com.example.kotlincrud.exception.BaseException
import com.example.kotlincrud.exception.BaseResponseCode
import com.example.kotlincrud.model.entity.UserDetailsImpl
import com.example.kotlincrud.model.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(private val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        val user = userRepository.findByEmail(email)

        if(user.isEmpty)
            throw BaseException(BaseResponseCode.USER_NOT_FOUND)

        return UserDetailsImpl(user.get())
    }
}