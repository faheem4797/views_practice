package com.example.viewspractice

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {


    private lateinit var countViewModel: CountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()

        installSplashScreen()
        setContentView(R.layout.activity_main)

        countViewModel = ViewModelProvider(this)[CountViewModel::class.java]

        val countButton = findViewById<Button>(R.id.btnAddCount)
        val countTextView = findViewById<TextView>(R.id.tvCount)

        countViewModel.count.observe(this, {
            countTextView.text = it.toString()
        })

        countButton.setOnClickListener {
            countViewModel.increment()

        }

    }

}