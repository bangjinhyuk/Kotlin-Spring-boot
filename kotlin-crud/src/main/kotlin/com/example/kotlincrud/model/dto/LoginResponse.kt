package com.example.kotlincrud.model.dto

data class LoginResponse (
    val accessToken: String,
    val refreshToken: String
)