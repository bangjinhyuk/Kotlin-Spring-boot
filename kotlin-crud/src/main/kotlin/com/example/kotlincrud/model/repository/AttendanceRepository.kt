package com.example.kotlincrud.model.repository

import com.example.kotlincrud.model.entity.Attendance
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by bangjinhyuk on 2022/06/12.
 */
interface AttendanceRepository : JpaRepository<Attendance, Long> {

}