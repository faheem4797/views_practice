package com.example.viewspractice

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.viewspractice.db.Student
import com.example.viewspractice.db.StudentDao
import com.example.viewspractice.db.StudentDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var btnSave: Button
    private lateinit var btnClear: Button
    private lateinit var rvStudents: RecyclerView
    private lateinit var rvAdapter : StudentRecyclerViewAdapter

    private  lateinit var studentViewModel: StudentViewModel

    private lateinit var currentStudent: Student

    private var isListItemClicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()

        installSplashScreen()
        setContentView(R.layout.activity_main)

        val studentDao = StudentDatabase.getInstance(application).studentDao()
        val studentViewModelFactory = StudentViewModelFactory(studentDao)
        studentViewModel = ViewModelProvider(this, studentViewModelFactory).get(StudentViewModel::class.java)

        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        btnSave = findViewById(R.id.btnSave)
        btnClear = findViewById(R.id.btnClear)
        rvStudents = findViewById(R.id.rvStudents)

        btnSave.setOnClickListener {
            if(isListItemClicked){
                updateStudentData()
                turnBtnsBackToSaveAndClear()
            }
            else{
                saveStudentData()
                clearInputs()
            }

        }

        btnClear.setOnClickListener {
            if(isListItemClicked){
                deleteStudentData()
                turnBtnsBackToSaveAndClear()
            }else{
                clearInputs()
            }

        }

        initRecyclerView()


    }

    private fun clearInputs(){
        etName.setText("")
        etEmail.setText("")
    }

    private fun turnBtnsBackToSaveAndClear(){
        btnSave.text = "Save"
        btnClear.text = "Clear"
        isListItemClicked = false
        clearInputs()
    }

    private fun saveStudentData(){
        studentViewModel.insertStudent(
            Student(
                0,
                etName.text.toString(),
                etEmail.text.toString(),
            )
        )
    }

    private fun updateStudentData(){
        studentViewModel.updateStudent(
            Student(
                currentStudent.id,
                etName.text.toString(),
                etEmail.text.toString(),
            )
        )
    }
    private fun deleteStudentData(){
        studentViewModel.deleteStudent(
            Student(
                currentStudent.id,
                etName.text.toString(),
                etEmail.text.toString(),
            )
        )
    }



    private fun initRecyclerView(){
        rvStudents.layoutManager = LinearLayoutManager(this)
        rvAdapter = StudentRecyclerViewAdapter{
            itemListClicked(it)
        }
        rvStudents.adapter = rvAdapter
        displayStudentsList()
    }

    private fun displayStudentsList(){
        studentViewModel.students.observe(this, {
           rvAdapter.setStudentList(it)
            rvAdapter.notifyDataSetChanged()
        })
    }

    private fun itemListClicked(student: Student){
//        Toast.makeText(this, "Clicked Student name is ${student.name}", Toast.LENGTH_SHORT).show()

        if(!isListItemClicked){
            currentStudent = student
            btnSave.text = "Update"
            btnClear.text = "Delete"
            isListItemClicked = true
            etName.setText(student.name)
            etEmail.setText(student.email)
        }else{
           turnBtnsBackToSaveAndClear()
        }

    }

}

