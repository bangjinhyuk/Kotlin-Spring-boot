package com.example.kotlincrud.model.entity

import javax.persistence.*

@Entity
@Table(name = "user")
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "name")
    var name: String,

    @Column(name = "password")
    var password: String,

    @Column(name = "gender")
    var gender: Gender

){
    fun createSimpleUser(): SimpleUser {
        return SimpleUser(
            id = id,
            name = name,
            gender = gender
        )
    }
    fun updateUser(requestUser: RequestUser): SimpleUser {
        password = requestUser.password
        gender = requestUser.gender

        return SimpleUser(
            id = id,
            name = name,
            gender = gender
        )
    }
}

enum class Gender {
    MALE,
    FEMALE
}

