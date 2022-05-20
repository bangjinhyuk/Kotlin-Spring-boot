package com.example.kotlincrud.model.entity

import com.example.kotlincrud.model.dto.SimpleUser
import com.example.kotlincrud.model.dto.UpdateRequest
import lombok.NoArgsConstructor

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.persistence.*

@Entity
@NoArgsConstructor
@Table(name = "user")
open class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id: Long? = null,

    @Column(name = "name")
    open var name: String,

    @Column(name = "email", unique = true)
    open var email: String,

    @Column(name = "password")
    open var password: String,

    @Column(name = "authority")
    open var role: String

): BaseEntity() {

    enum class Authority(val role: String) {
        ROLE_USER("ROLE_USER"), ROLE_ADMIN("ROLE_ADMIN")
    }

    fun makeSimpleUser(): SimpleUser {
        return SimpleUser(
            id = id,
            name = name,
            email = email
        )
    }

    fun updateUser(updateRequest: UpdateRequest){
        this.name = updateRequest.name?: name
        this.password = BCryptPasswordEncoder().encode(updateRequest.password)?: password
        this.role = updateRequest.role?: role
    }
}

