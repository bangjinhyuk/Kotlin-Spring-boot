package com.example.kotlincrud.model.dto

import com.example.kotlincrud.model.entity.Gender

data class SimpleUser (
    val id: Long?,
    val name: String,
    val gender: Gender
)