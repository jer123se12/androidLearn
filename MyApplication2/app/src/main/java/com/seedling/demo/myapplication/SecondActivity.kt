package com.seedling.demo.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val name=findViewById<TextView>(R.id.Name)
        val userName=intent.getStringExtra("UserId")
        name.text="Welcome $userName you have new add palcement in this view now yes please"

    }
}