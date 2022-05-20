package com.example.kotlincrud.service

import com.example.kotlincrud.model.entity.User

interface UserService {

    fun findAllUsers(): List<User>
    fun createUser(requestUser: RequestUser): SimpleUser
    fun login(name: String, password: String): Any?
    fun update(requestUser: RequestUser): SimpleUser

}