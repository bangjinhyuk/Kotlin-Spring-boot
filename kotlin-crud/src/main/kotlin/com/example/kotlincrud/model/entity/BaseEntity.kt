package com.example.kotlincrud.model.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@EntityListeners(value = [AuditingEntityListener::class])
@MappedSuperclass
abstract class BaseEntity(
){
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "DATETIME")
    open var createdAt: LocalDateTime? = null

    @LastModifiedDate
    @Column(name = "updated_at", columnDefinition = "DATETIME")
    open var updatedAt: LocalDateTime = LocalDateTime.now()
}