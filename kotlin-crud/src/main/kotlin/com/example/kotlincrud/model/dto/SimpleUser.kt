package com.example.kotlincrud.model.dto

import com.example.kotlincrud.model.entity.User

data class SimpleUser (
    val id: Long?,
    val name: String,
    val email: String
)