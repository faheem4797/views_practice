package com.example.viewspractice

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {


    val fruitsList = listOf<Fruit>(
        Fruit("apple", "faheem"),
        Fruit("mango", "pheem"),
        Fruit("pear", "neem"),
        Fruit("peach", "peem"),
        Fruit("banana", "bheem"),
        Fruit("orange", "jeem"),
        Fruit("apricot", "reem"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()

        installSplashScreen()
        setContentView(R.layout.activity_main)

        val recyclerViewWidget = findViewById<RecyclerView>(R.id.rvList)
        recyclerViewWidget.setBackgroundColor(Color.YELLOW)
        recyclerViewWidget.layoutManager = LinearLayoutManager(this)
        recyclerViewWidget.adapter = MyRecyclerViewAdapter(fruitsList
        ) { selectedItem: Fruit ->
            displayToastForFruits(selectedItem)
        }


    }

    private fun displayToastForFruits(fruit: Fruit){
        Toast.makeText(this, "${fruit.name} is supplied by ${fruit.supplier}" , Toast.LENGTH_SHORT).show()
    }

}