package com.example.kotlincrud.controller

import com.example.kotlincrud.model.dto.RequestUser
import com.example.kotlincrud.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @GetMapping("/users")
    private fun getUsers(): ResponseEntity<Any> {
        return ResponseEntity.ok(userService.findAllUsers())
    }

    @PostMapping("/user")
    private fun createUser(@RequestBody requestUser: RequestUser): ResponseEntity<Any> {
        return ResponseEntity.ok(userService.createUser(requestUser))
    }

    @GetMapping("/login")
    private fun login(@RequestParam name: String, @RequestParam password: String) : ResponseEntity<Any> {
        return ResponseEntity.ok(userService.login(name,password))
    }

    @PatchMapping("/user")
    private fun update(@RequestBody requestUser: RequestUser) : ResponseEntity<Any> {
        return ResponseEntity.ok(userService.update(requestUser))
    }
}