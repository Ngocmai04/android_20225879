package com.example.studentmanager.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studentmanager.model.Student

class StudentViewModel : ViewModel() {

    // Internal mutable storage (the requirement says MutableList in ViewModel)
    private val studentsStore: MutableList<Student> = mutableListOf(
        Student("20210001", "Nguyễn Văn A", "0900000001", "Hà Nội"),
        Student("20210002", "Trần Thị B", "0900000002", "Hải Phòng")
    )

    private val _students = MutableLiveData<List<Student>>(studentsStore.toList())
    val students: LiveData<List<Student>> = _students

    fun addStudent(student: Student): Boolean {
        // MSSV must be unique
        if (studentsStore.any { it.mssv == student.mssv }) return false
        studentsStore.add(student)
        _students.value = studentsStore.toList()
        return true
    }

    fun deleteStudent(mssv: String) {
        studentsStore.removeAll { it.mssv == mssv }
        _students.value = studentsStore.toList()
    }

    fun getStudentByMssv(mssv: String): Student? {
        return studentsStore.firstOrNull { it.mssv == mssv }
    }

    fun updateStudent(updated: Student): Boolean {
        val index = studentsStore.indexOfFirst { it.mssv == updated.mssv }
        if (index == -1) return false
        studentsStore[index] = updated
        _students.value = studentsStore.toList()
        return true
    }
}
