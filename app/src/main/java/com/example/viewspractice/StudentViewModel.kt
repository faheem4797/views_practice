package com.example.viewspractice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viewspractice.db.Student
import com.example.viewspractice.db.StudentDao
import kotlinx.coroutines.launch

class StudentViewModel(private val dao: StudentDao) : ViewModel() {

    val students = dao.getAllStudents()

    fun insertStudent(student: Student) = viewModelScope.launch {
        dao.insertStudent(student)
    }

    fun updateStudent(student: Student) = viewModelScope.launch {
        dao.updateStudent(student)
    }

    fun deleteStudent(student: Student) = viewModelScope.launch {
        dao.deleteStudent(student)
    }
}