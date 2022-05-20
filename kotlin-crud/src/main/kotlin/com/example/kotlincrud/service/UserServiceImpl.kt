package com.example.kotlincrud.service

import com.example.kotlincrud.exception.BaseException
import com.example.kotlincrud.exception.BaseResponseCode
import com.example.kotlincrud.model.dto.*
import com.example.kotlincrud.model.entity.User
import com.example.kotlincrud.model.repository.UserRepository
import com.example.kotlincrud.utils.JwtUtils
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val jwtUtils: JwtUtils
) : UserService {

    @Transactional(readOnly = true)
    override fun findAllUsers(): List<User>  = userRepository.findAllBy()


    @Transactional
    override fun createUser(registerRequest: RegisterRequest): SimpleUser = userRepository.save(registerRequest.toUser()).makeSimpleUser()

    @Transactional(readOnly = true)
    override fun login(loginRequest: LoginRequest): LoginResponse {
        var user = userRepository.findByEmail(loginRequest.email)

        if(user.isPresent) {
            if(BCryptPasswordEncoder().matches(loginRequest.password, user.get().password)) {
                return jwtUtils.createToken(user.get())
            }else
                throw BaseException(BaseResponseCode.INVALID_PASSWORD)
        }else
            throw BaseException(BaseResponseCode.USER_NOT_FOUND)
    }

    @Transactional
    override fun update(user: User, updateRequest: UpdateRequest): SimpleUser? {
        user.updateUser(updateRequest)
        userRepository.save(user)
        return user.makeSimpleUser()
    }

}