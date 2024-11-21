package com.example.viewspractice

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private var count = 0
    private lateinit var tvMessage: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()

        installSplashScreen()
        setContentView(R.layout.activity_main)

        val tvCount = findViewById<TextView>(R.id.tvCount)
        val btnCount = findViewById<Button>(R.id.btnCount)
        val btnDownload = findViewById<Button>(R.id.btnDownload)
        tvMessage = findViewById<TextView>(R.id.tvMessage)

        btnCount.setOnClickListener {
            count++
            tvCount.text = count.toString()
        }

        btnDownload.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {
                downloadUserData()
            }
        }
    }

    private suspend fun downloadUserData() {
        for (i in 1..200000) {
            Log.i(
                "pheeem", "Downloading User $i from thread: ${Thread.currentThread().name}"
            )
            withContext(Dispatchers.Main){
                tvMessage.text =  "Downloading User $i"
            }

            delay(100)
        }
    }
}

