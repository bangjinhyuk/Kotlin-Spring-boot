package com.example.kotlincrud.service

import com.example.kotlincrud.model.dto.RegisterRequest
import com.example.kotlincrud.model.entity.User
import com.example.kotlincrud.model.repository.UserRepository
import com.example.kotlincrud.utils.JwtUtils
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.BDDMockito.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

/**
 * Created by bangjinhyuk on 2022/05/27.
 */
@ExtendWith(MockitoExtension::class)
internal class UserServiceImplTest{
    @InjectMocks
    lateinit var userServiceImpl: UserServiceImpl

    @Mock
    lateinit var userRepository: UserRepository

    @Mock
    private lateinit var jwtUtils: JwtUtils

    @Mock
    private lateinit var userDetailsServiceImpl: UserDetailsServiceImpl

    @Test
    fun createUserTest(){
        //given
        val registerRequest =
            RegisterRequest(
                name = "방진혁",
                email = "jinhyuk0103@gmail.com",
                password = "123456",
                role = null
            )

        var captor: ArgumentCaptor<User> = ArgumentCaptor.forClass(User::class.java)

        given(userRepository.save(any()))
            .willReturn(registerRequest.toUser())

        //when
        userServiceImpl.createUser(registerRequest)


        //then
        Mockito.verify(userRepository, times(1))
            .save(captor.capture())

        val capture = captor.value
        assertEquals(registerRequest.name, capture.name)
        assertEquals(registerRequest.email, capture.email)
        assertEquals(true, BCryptPasswordEncoder().matches(registerRequest.password,capture.password))

    }
}