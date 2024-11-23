package com.example.viewspractice

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.viewspractice.databinding.ActivityMainBinding
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var serviceIntent: Intent

    private var isStarted = false

    private var currentTime = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()

        installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.apply { 
            btnStart.setOnClickListener{
                startOrStop()
//                Log.i(TAG, " Starting Service")
//                startService(serviceIntent)
            }
            btnReset.setOnClickListener{
                reset()
//                Log.i(TAG, " Stopping Service")
//                stopService(serviceIntent)
            }
        }

        serviceIntent = Intent(applicationContext, StopWatchService::class.java)
        registerReceiver(
            updateTime, IntentFilter(StopWatchService.UPDATED_TIME), RECEIVER_NOT_EXPORTED
            )


    }

    private fun startOrStop(){
        if(isStarted){
            stop()
        }else{
            start()
        }
    }

    private fun start(){
        serviceIntent.putExtra(StopWatchService.CURRENT_TIME, currentTime)
        startService(serviceIntent)
        binding.btnStart.text = "STOP"
        isStarted = true
    }

    private fun stop(){
        stopService(serviceIntent)
        binding.btnStart.text = "START"
        isStarted = false
    }

    private fun reset(){
        stop()
        currentTime = 0.0
        binding.tvTime.text = getFormattedTime(currentTime)
    }

    private val updateTime : BroadcastReceiver = object:BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            if(intent != null){
                currentTime = intent.getDoubleExtra(StopWatchService.UPDATED_TIME,0.0)
                binding.tvTime.text = getFormattedTime(currentTime)
            }
        }
    }

    private fun getFormattedTime(time:Double):String{
        val timeInt = time.roundToInt()
        val hours = timeInt % 86400 / 3600
        val minutes = timeInt % 86400 % 3600 / 60
        val seconds = timeInt % 86400 % 3600 % 60

        return String.format("%02d:%02d:%02d",hours,minutes,seconds)
    }



}