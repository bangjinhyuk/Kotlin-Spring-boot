package com.example.kotlincrud.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.sql.DriverManager.println

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(BaseException::class)
    protected fun handleBaseException(e:BaseException): ResponseEntity<Any> {
        return ResponseEntity.status(e.baseResponseCode.status)
            .body(BaseRes(e.baseResponseCode.status, e.baseResponseCode.message))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    protected fun handleConstraintViolationException(e: MethodArgumentNotValidException): ResponseEntity<Any> {
        val ee = mutableMapOf<String, String>()
        for (error in e.fieldErrors) {
            ee.put(error.field, error.defaultMessage.toString())
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(BaseRes(HttpStatus.BAD_REQUEST, ee.toString()))
    }


    data class BaseRes (val status: HttpStatus, val message: String)
}


