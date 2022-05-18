package com.example.kotlincrud.model.repository

import com.example.kotlincrud.model.entity.User
import org.springframework.data.repository.CrudRepository
import java.util.*


interface UserRepository : CrudRepository<User, Long>{
    fun findAllBy(): List<User>
    fun findByNameAndPassword(name: String, password: String): Optional<User>
    fun findByName(name: String): Optional<User>
}