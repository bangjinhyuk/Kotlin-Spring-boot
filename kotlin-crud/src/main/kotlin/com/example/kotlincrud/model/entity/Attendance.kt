package com.example.kotlincrud.model.entity

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * Created by bangjinhyuk on 2022/06/12.
 */
@Entity
data class Attendance(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    var method: Method,
    var type: AttendanceType,
    var checkedAt: LocalDateTime
):BaseEntity() {
    enum class Method {
        CARD, MOBILE
    }
    enum class AttendanceType {
        WORKIN, WORKOUT, OUTING
    }
}

