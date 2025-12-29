package com.example.studentmanager.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class Student(
    @PrimaryKey
    val mssv: String,
    val name: String,
    val phone: String,
    val address: String
)
