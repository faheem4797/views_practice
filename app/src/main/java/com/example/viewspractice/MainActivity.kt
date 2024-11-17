package com.example.viewspractice

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText

import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {

    private lateinit var textName : EditText
    private lateinit var textAge : EditText
    private lateinit var sf: SharedPreferences
    private  lateinit var editor: SharedPreferences.Editor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()

        installSplashScreen()
        setContentView(R.layout.activity_main)
        textName = findViewById(R.id.etName)
        textAge =findViewById(R.id.etAge)
        sf = getSharedPreferences("my_sf", MODE_PRIVATE)
        editor = sf.edit()

    }

    override fun onPause() {

        super.onPause()
        val name = textName.text.toString()
        val age = textAge.text.toString().toInt()

        editor.apply {
            putString("sf_name", name)
            putInt("sf_age",age)
            commit()
        }
    }

    override fun onResume() {
        super.onResume()
        val name = sf.getString("sf_name", null)
        val age = sf.getInt("sf_age", 0)

        textName.setText(name)
        if(age != 0){
            textAge.setText(age.toString())
        }


    }

}