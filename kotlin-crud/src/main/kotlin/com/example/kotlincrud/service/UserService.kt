package com.example.kotlincrud.service

import com.example.kotlincrud.model.dto.*
import com.example.kotlincrud.model.entity.User

interface UserService {

    fun findAllUsers(): List<User>
    fun createUser(registerRequest: RegisterRequest): SimpleUser
    fun login(loginRequest: LoginRequest): LoginResponse
    fun update(user:User, updateRequest: UpdateRequest): SimpleUser?

}