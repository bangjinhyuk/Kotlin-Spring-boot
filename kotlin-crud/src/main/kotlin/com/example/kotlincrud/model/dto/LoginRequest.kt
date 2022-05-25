package com.example.kotlincrud.model.dto

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotBlank

data class LoginRequest(
    @field:NotBlank(message = "이메일을 입력해 주세요.") var email: String,
    @field:Length(min = 4, max = 10, message = "비밀번호는 4~10자리 입니다.") var password: String
) {
}