package com.example.kotlincrud.controller

import com.example.kotlincrud.model.dto.RegisterRequest
import com.example.kotlincrud.model.repository.UserRepository
import com.example.kotlincrud.service.UserDetailsServiceImpl
import com.example.kotlincrud.service.UserService
import com.example.kotlincrud.utils.JwtUtils
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import java.nio.charset.StandardCharsets
import javax.management.loading.ClassLoaderRepository

/**
 * Created by bangjinhyuk on 2022/05/27.
 */
@WebMvcTest
internal class UserControllerTest{
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var userService: UserService

    @MockBean
    private lateinit var jwtUtils: JwtUtils

    @MockBean
    private lateinit var userRepository: UserRepository

    @MockBean
    private lateinit var userDetailsServiceImpl: UserDetailsServiceImpl

    var mediaType = MediaType(MediaType.APPLICATION_JSON.type, MediaType.APPLICATION_JSON.subtype, StandardCharsets.UTF_8)



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
        given(userService.createUser(registerRequest))
            .willReturn(registerRequest.toUser().makeSimpleUser())


        //when, then
        mockMvc.perform(post("/user").content(objectMapper.writeValueAsString(registerRequest)).contentType(mediaType))
            .andExpect(status().isOk)
            .andDo(print())
            .andExpect(
                jsonPath("$.name").value("방진혁")
            )
    }


}