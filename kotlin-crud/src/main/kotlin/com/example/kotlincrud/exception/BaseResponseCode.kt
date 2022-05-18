package com.example.kotlincrud.exception

import org.springframework.http.HttpStatus

enum class BaseResponseCode(val status: HttpStatus, val message: String) {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND,"사용자를 찾을수 없습니다.");

}