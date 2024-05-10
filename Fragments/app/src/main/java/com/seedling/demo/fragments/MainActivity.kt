package com.seedling.demo.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val firstF=FirstFrag()
        val secondF=SecondFrag()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frag1,firstF)
            commit()
        }
        findViewById<Button>(R.id.btn1).setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.frag1,firstF)
                addToBackStack(null)
                commit()
            }
        }
        findViewById<Button>(R.id.btn2).setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.frag1,secondF)
                addToBackStack(null)
                commit()
            }
        }
    }
}