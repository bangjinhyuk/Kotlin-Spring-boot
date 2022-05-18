package com.example.kotlincrud.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.sql.DriverManager.println

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(BaseException::class)
    protected fun handleBaseException(e:BaseException): ResponseEntity<Any> {
        System.out.println("${e.baseResponseCode.message} - ${e.baseResponseCode.status}")
        return ResponseEntity.status(e.baseResponseCode.status)
            .body(BaseRes(e.baseResponseCode.status, e.baseResponseCode.message))
    }
    data class BaseRes (val status: HttpStatus, val message: String)
}


