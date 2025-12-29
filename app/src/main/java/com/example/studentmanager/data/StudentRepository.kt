package com.example.studentmanager.data

import com.example.studentmanager.model.Student

class StudentRepository(private val dao: StudentDao) {

    val students = dao.getAllStudents()

    suspend fun addStudent(student: Student) {
        dao.insertStudent(student)
    }

    suspend fun updateStudent(student: Student) {
        dao.updateStudent(student)
    }

    suspend fun deleteStudent(mssv: String) {
        dao.deleteStudent(mssv)
    }

    suspend fun getStudentByMssv(mssv: String): Student? {
        return dao.getStudentByMssv(mssv)
    }
}
