package com.example.kotlincrud.model.dto

import com.example.kotlincrud.model.entity.Gender
import javax.persistence.Column

data class RequestUser(
    var name: String,

    var password: String,

    var gender: Gender
)
