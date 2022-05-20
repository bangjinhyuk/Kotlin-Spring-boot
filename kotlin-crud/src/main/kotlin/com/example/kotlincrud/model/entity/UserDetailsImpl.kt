package com.example.kotlincrud.model.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsImpl(val user: User) : UserDetails {

    var enabled: Boolean = true

    override fun getAuthorities(): MutableCollection<out GrantedAuthority>{
        val authorities = AuthorityUtils.createAuthorityList()
        authorities.add(SimpleGrantedAuthority(user.role))
        return authorities
    }

    override fun getPassword(): String = user.password

    override fun getUsername(): String = user.email

    override fun isAccountNonExpired(): Boolean = enabled

    override fun isAccountNonLocked(): Boolean = enabled

    override fun isCredentialsNonExpired(): Boolean = enabled

    override fun isEnabled(): Boolean = enabled
}