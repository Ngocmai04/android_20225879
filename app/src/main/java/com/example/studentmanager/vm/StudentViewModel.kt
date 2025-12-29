package com.example.studentmanager.vm

import android.app.Application
import androidx.lifecycle.*
import com.example.studentmanager.data.AppDatabase
import com.example.studentmanager.data.StudentRepository
import com.example.studentmanager.model.Student
import kotlinx.coroutines.launch

class StudentViewModel(application: Application) : AndroidViewModel(application) {

    private val repo: StudentRepository

    val students: LiveData<List<Student>>

    private val _selectedStudent = MutableLiveData<Student?>()
    val selectedStudent: LiveData<Student?> = _selectedStudent

    init {
        val dao = AppDatabase.getInstance(application).studentDao()
        repo = StudentRepository(dao)
        students = repo.students.asLiveData()
    }

    fun loadStudent(mssv: String) {
        viewModelScope.launch {
            _selectedStudent.value = repo.getStudentByMssv(mssv)
        }
    }

    fun clearSelectedStudent() {
        _selectedStudent.value = null
    }

    fun addStudent(student: Student) = viewModelScope.launch {
        repo.addStudent(student)
    }

    fun updateStudent(student: Student) = viewModelScope.launch {
        repo.updateStudent(student)
    }

    fun deleteStudent(mssv: String) = viewModelScope.launch {
        repo.deleteStudent(mssv)
    }
}
