package com.seedling.demo.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.graphics.toColorInt

class MainActivity : AppCompatActivity() {
    private lateinit var hf:EditText
    private lateinit var wf:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hf=findViewById<EditText>(R.id.tfheight)
        wf=findViewById<EditText>(R.id.tfweight)
        val calcbtn=findViewById<Button>(R.id.CalcBtn)
        findViewById<CardView>(R.id.bmi).visibility= View.INVISIBLE

        calcbtn.setOnClickListener {
            val raww = wf.text.toString()
            val rawh = hf.text.toString()
            if (validateInput(raww,rawh)) {


                val weight = raww.toFloat()
                val height = (rawh.toFloat()) / 100
                val bmi = weight / (height * height)
                findViewById<TextView>(R.id.bminum).setText(String.format("%.2f", bmi))
                var texttoshow = ""
                var c: String
                if (bmi < 18.5) {
                    texttoshow = "You are Underweight"
                    c = "#FF0000"
                } else if (bmi > 24.9) {
                    texttoshow = "You are Overweight"
                    c = "#00FF00"
                } else {
                    texttoshow = "You are Healthy"
                    c = "#FF0000"
                }
                findViewById<TextView>(R.id.bmitext).setText(texttoshow.toString())
                findViewById<TextView>(R.id.bminum).setTextColor(c.toColorInt())
                findViewById<CardView>(R.id.bmi).visibility = View.VISIBLE
            }
        }
    }
    private fun validateInput(weight:String?, height: String?):Boolean{
        return when{
            weight.isNullOrEmpty()->{
                Toast.makeText(this, "Weight is empty", Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty()->{
                Toast.makeText(this, "Height is empty", Toast.LENGTH_LONG).show()
                return false
            }

            else -> {
                return true
            }
        }
    }
}

