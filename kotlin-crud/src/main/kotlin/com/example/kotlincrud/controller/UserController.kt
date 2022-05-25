package com.example.kotlincrud.controller

import com.example.kotlincrud.model.dto.*
import com.example.kotlincrud.model.entity.User
import com.example.kotlincrud.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

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
    private fun createUser(@Valid  @RequestBody registerRequest: RegisterRequest): ResponseEntity<SimpleUser> {
        return ResponseEntity.ok(userService.createUser(registerRequest))
    }

    @PostMapping("/login")
    private fun login(@Valid @RequestBody loginRequest: LoginRequest) : ResponseEntity<LoginResponse> {
        return ResponseEntity.ok(userService.login(loginRequest))
    }

    @PatchMapping("/user")
    private fun update(@RequestAttribute user: User, @RequestBody updateRequest: UpdateRequest) : ResponseEntity<SimpleUser> {
        return ResponseEntity.ok(userService.update(user, updateRequest))
    }
}