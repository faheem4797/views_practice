package com.example.viewspractice

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import java.util.Locale

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()

        installSplashScreen()
        setContentView(R.layout.activity_main)

        val weightText = findViewById<EditText>(R.id.etWeight)
        val heightText = findViewById<EditText>(R.id.etHeight)
        val calcButton = findViewById<Button>(R.id.btnCalculate)

        calcButton.setOnClickListener {

            val view: View? = this.currentFocus
            if (view != null) {
                view.clearFocus()
                val inputMethodManager =
                    getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            }

            val weight = weightText.text.toString()
            val height = heightText.text.toString()

            if (validateInput(weight, height)) {

                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                val bmiShort = String.format(Locale.getDefault(), "%.2f", bmi).toFloat()
                displayResult(bmiShort)
            }

        }


    }

    private fun validateInput(weight: String, height: String): Boolean {
        when {
            weight.isEmpty() -> {
                Toast.makeText(this, "Please enter a valid weight", Toast.LENGTH_SHORT).show()
                return false
            }
            height.isEmpty() -> {
                Toast.makeText(this, "Please enter a valid height", Toast.LENGTH_SHORT).show()
                return false
            }
            else -> {
                return true
            }
        }
    }

    private fun displayResult(bmi: Float) {
        val bmiNumber = findViewById<TextView>(R.id.tvBmiNumber)
        val bmiDescription = findViewById<TextView>(R.id.tvBmiDescription)
        val bmiInfo = findViewById<TextView>(R.id.tvBmiInfo)

        bmiNumber.text = bmi.toString()
        bmiInfo.text = "Normal range is 18.5 - 24.9"

        var resultText = ""
        var color = 0

        when {
            bmi < 18.50 -> {
                resultText = "Underweight"
                color = R.color.under_weight
            }

            bmi in 18.50..24.99 -> {
                resultText = "Healthy"
                color = R.color.normal
            }

            bmi in 25.00..29.99 -> {
                resultText = "Overweight"
                color = R.color.over_weight
            }

            else -> {
                resultText = "Obese"
                color = R.color.obese
            }
        }

        bmiDescription.text = resultText
        bmiDescription.setTextColor(ContextCompat.getColor(this, color))

    }


}