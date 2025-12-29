package com.example.studentmanager.data

import androidx.room.*
import com.example.studentmanager.model.Student
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {

    @Query("SELECT * FROM students")
    fun getAllStudents(): Flow<List<Student>>

    @Query("SELECT * FROM students WHERE mssv = :mssv LIMIT 1")
    suspend fun getStudentByMssv(mssv: String): Student?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertStudent(student: Student)

    @Update
    suspend fun updateStudent(student: Student)

    @Query("DELETE FROM students WHERE mssv = :mssv")
    suspend fun deleteStudent(mssv: String)
}
