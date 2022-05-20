package com.example.kotlincrud.exception

import org.springframework.http.HttpStatus

enum class BaseResponseCode(val status: HttpStatus, val message: String) {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND,"사용자를 찾을수 없습니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST,"유효하지 않은 비밀번호 입니다.")
}