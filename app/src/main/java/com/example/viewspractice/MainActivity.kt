package com.example.viewspractice

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.viewspractice.databinding.ActivityMainBinding
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var retService: AlbumService

    private lateinit var binding: ActivityMainBinding

    private val channelID = "com.example.viewspractice.demoNotification"

    private lateinit var notificationManager : NotificationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()

        installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel(channelID, "DemoChannel" , "Demo Notification Channel")

        binding.btnNotification.setOnClickListener {
            displayNotification()
        }





    }

    private fun createNotificationChannel(id : String, name: String, channelDescription: String){
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(id,name, importance).apply {
             description = channelDescription
        }
        notificationManager.createNotificationChannel(channel)
    }

    private fun displayNotification(){
        val notificationId = 48
        val notification = NotificationCompat.Builder(this@MainActivity,channelID)
            .setContentTitle("Demo Title")
            .setContentText("Demo Text of Notification")
            .setSmallIcon(R.drawable.zombatar)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        notificationManager.notify(notificationId, notification)
    }



}