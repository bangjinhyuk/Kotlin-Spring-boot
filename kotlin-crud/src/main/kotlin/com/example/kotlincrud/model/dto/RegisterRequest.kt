package com.example.kotlincrud.model.dto

import com.example.kotlincrud.model.entity.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

data class RegisterRequest(

    var name: String,

    var email: String,

    var password: String,

    var role: String?

){

    fun toUser() : User {
        return User(
            name = name,
            email = email,
            password = BCryptPasswordEncoder().encode(password),
            role = User.Authority.ROLE_USER.role
        )
    }

}
