package com.example.viewspractice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.viewspractice.databinding.ActivityMainBinding
import com.example.viewspractice.databinding.ListItemBinding
import com.example.viewspractice.db.Student
import com.example.viewspractice.db.StudentDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    private lateinit var rvAdapter : StudentRecyclerViewAdapter

    private  lateinit var studentViewModel: StudentViewModel

    private lateinit var currentStudent: Student

    private var isListItemClicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()

        installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val studentDao = StudentDatabase.getInstance(application).studentDao()
        val studentViewModelFactory = StudentViewModelFactory(studentDao)
        studentViewModel = ViewModelProvider(this, studentViewModelFactory).get(StudentViewModel::class.java)



        binding.apply {
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

        }
        initRecyclerView()


    }

    private fun clearInputs(){
        binding.apply {
            etName.setText("")
            etEmail.setText("")
        }
    }

    private fun turnBtnsBackToSaveAndClear(){
        binding.apply {
            btnSave.text = "Save"
            btnClear.text = "Clear"
            isListItemClicked = false
            clearInputs()
        }
    }

    private fun saveStudentData(){
        binding.apply {
            studentViewModel.insertStudent(
                Student(
                    0,
                    etName.text.toString(),
                    etEmail.text.toString(),
                )
            )
        }
    }

    private fun updateStudentData(){
        binding.apply {
        studentViewModel.updateStudent(
            Student(
                currentStudent.id,
                etName.text.toString(),
                etEmail.text.toString(),
            )
        )}
    }
    private fun deleteStudentData(){
        binding.apply {
        studentViewModel.deleteStudent(
            Student(
                currentStudent.id,
                etName.text.toString(),
                etEmail.text.toString(),
            )
        )}
    }



    private fun initRecyclerView(){
        binding.rvStudents.layoutManager = LinearLayoutManager(this)
        rvAdapter = StudentRecyclerViewAdapter{
            itemListClicked(it)
        }
        binding.rvStudents.adapter = rvAdapter
        displayStudentsList()
    }

    private fun displayStudentsList(){
        studentViewModel.students.observe(this, {
            rvAdapter.setStudentList(it)
            rvAdapter.notifyDataSetChanged()
        })
    }

    private fun itemListClicked(student: Student){

        if(!isListItemClicked){
            binding.apply {
                currentStudent = student
                btnSave.text = "Update"
                btnClear.text = "Delete"
                isListItemClicked = true
                etName.setText(student.name)
                etEmail.setText(student.email)
            }
        }else{
            turnBtnsBackToSaveAndClear()
        }

    }

}