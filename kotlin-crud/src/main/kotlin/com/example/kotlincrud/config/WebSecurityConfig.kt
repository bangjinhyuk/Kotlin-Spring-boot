package com.example.kotlincrud.config

import com.example.kotlincrud.filter.JwtFilter
import com.example.kotlincrud.service.UserDetailsServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class WebSecurityConfig(
    private val userDetailsService: UserDetailsServiceImpl,
    private val jwtFilter: JwtFilter
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {

        http.csrf().disable()

        http.authorizeRequests()
            .antMatchers(HttpMethod.POST,"/user").permitAll()
            .antMatchers(HttpMethod.POST,"/login").permitAll()
            .antMatchers(HttpMethod.PATCH,"/user").permitAll()
            .antMatchers(HttpMethod.GET,"/users").hasAuthority("ROLE_ADMIN")
            .anyRequest().authenticated()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }
}