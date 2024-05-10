package com.seedling.demo.viewmodeldemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    var count=0
    private lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel=ViewModelProvider(this).get(MainActivityViewModel::class.java)
        val textv=findViewById<TextView>(R.id.nview)
        val btn=findViewById<Button>(R.id.btncounter)
//        textv.text= viewModel.count.toString()
        viewModel.count.observe(this,{
            textv.text=it.toString()
        })
        btn.setOnClickListener {
            viewModel.updateCount()
//            textv.text= viewModel.count.toString()
        }
    }
}