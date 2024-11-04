package com.example.viewspractice

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        Thread.sleep(3000)
        installSplashScreen()
        setContentView(R.layout.activity_main)
        val explicitButton = findViewById<Button>(R.id.btnExplicitButton)

        explicitButton.setOnClickListener{
            val explicitIntent = Intent(this, SecondActivity::class.java)
            startActivity(explicitIntent)
            finish()
        }

        val url = "https://www.google.com"
        val implicitButton = findViewById<Button>(R.id.btnImplicitButton)
        implicitButton.setOnClickListener {
            val implicitIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity((implicitIntent))
        }
    }
}