package com.example.kotlincrud.model.dto

import com.example.kotlincrud.model.entity.User
import org.hibernate.validator.constraints.Length
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class RegisterRequest(

    @field:NotBlank(message = "이름을 입력해 주세요.") @field:Length(min = 2,max = 5, message = "이름은 2~5자리 입니다.") var name: String,

    @field:NotBlank(message = "이메일을 입력해 주세요") @field:Email(message = "이메일 형식이어야 합니다.") var email: String,

    @field:NotBlank(message = "비밀번호를 입력해 주세요.") @field:Length(min = 4,max = 10, message = "비밀번호는 4~10자리 입니다.") var password: String,

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
