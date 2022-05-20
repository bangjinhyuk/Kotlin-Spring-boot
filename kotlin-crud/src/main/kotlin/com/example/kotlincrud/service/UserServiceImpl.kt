package com.example.kotlincrud.service

import com.example.kotlincrud.exception.BaseException
import com.example.kotlincrud.exception.BaseResponseCode
import com.example.kotlincrud.model.entity.User
import com.example.kotlincrud.model.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {

    @Transactional(readOnly = true)
    override fun findAllUsers(): List<User> {
        return userRepository.findAllBy()
    }

    @Transactional
    override fun createUser(requestUser: RequestUser): SimpleUser {

        var user = User(
            name = requestUser.name,
            password = requestUser.password,
            gender = requestUser.gender
        )

        return userRepository.save(user).createSimpleUser()
    }

    @Transactional(readOnly = true)
    override fun login(name: String, password: String): Any? {
        var user = userRepository.findByNameAndPassword(name,password)
        if(user.isPresent)
            return "Token"
        else
            throw BaseException(BaseResponseCode.USER_NOT_FOUND)
    }

    @Transactional
    override fun update(requestUser: RequestUser): SimpleUser {
        var user = userRepository.findByName(requestUser.name)

        if(user.isPresent)
            return user.get().updateUser(requestUser)
        else
            throw BaseException(BaseResponseCode.USER_NOT_FOUND)
    }

}