package com.example.viewspractice

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        Thread.sleep(3000)
        installSplashScreen()
        setContentView(R.layout.activity_main)
       val listView : ListView = findViewById(R.id.listView)
        val listItems = arrayOf<String>(
            "Read a book",
            "Exercise",
            "Code",
            "Go for Shopping",
            "Eat 3 times"
        )

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,listItems)
        listView.adapter = arrayAdapter

        listView.setOnItemClickListener{ parent: AdapterView<*>, view2: View, position: Int, id: Long ->
            val selectedItem = parent.getItemAtPosition(position) as String
            Toast.makeText(this, "Clicked on $selectedItem" , Toast.LENGTH_SHORT).show()

        }



    }
}