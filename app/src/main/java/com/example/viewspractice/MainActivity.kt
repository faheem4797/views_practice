package com.example.viewspractice

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<RecycleViewDataClass>
    lateinit var titleList: Array<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        Thread.sleep(3000)
        installSplashScreen()
        setContentView(R.layout.activity_main)

        titleList = arrayOf(
            "Array One",
            "Array Two",
            "Array Three",
            "Array Four",
            "Array Five",
            "Array Six",
            "Array Seven",
            "Array One",
            "Array Two",
            "Array Three",
            "Array Four",
            "Array Five",
            "Array Six",
            "Array Seven"
        )

        recyclerView = findViewById(R.id.recycleView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        dataList = arrayListOf<RecycleViewDataClass>()
        getData()




    }
   private fun getData(){
        for (i in titleList.indices){
            val dataClass =  RecycleViewDataClass(titleList[i])
            dataList.add(dataClass)
        }
        recyclerView.adapter = AdapterClass(dataList)
    }
}