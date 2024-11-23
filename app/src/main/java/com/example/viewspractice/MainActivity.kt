package com.example.viewspractice

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.viewspractice.MyBackgroundService.Companion.MARKS
import com.example.viewspractice.MyBackgroundService.Companion.NAME
import com.example.viewspractice.MyBackgroundService.Companion.TAG
import com.example.viewspractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()

        installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val serviceIntent = Intent(this, MyBackgroundService::class.java)
        serviceIntent.putExtra(NAME,"faheem")
        serviceIntent.putExtra(MARKS,80)
        
        binding.apply { 
            btnStart.setOnClickListener{
                Log.i(TAG, " Starting Service")
                startService(serviceIntent)
            }
            btnStop.setOnClickListener{
                Log.i(TAG, " Stopping Service")
                stopService(serviceIntent)
            }
        }

    }



}