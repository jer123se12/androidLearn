package com.seedling.demo.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val greetTextView=findViewById<TextView>(R.id.tvHello)
        val inputField=findViewById<EditText>(R.id.nameText)
        val btn=findViewById<Button>(R.id.submit_button)
        val navBtn=findViewById<Button>(R.id.NavBtn)
        var enteredname=""
        btn.setOnClickListener {

            enteredname=inputField.text.toString()
            if (enteredname!="") {
                val message = "Welcome $enteredname"
                greetTextView.text = message
                inputField.text.clear()
                navBtn.visibility= View.VISIBLE
            }else{
                greetTextView.text=""
                Toast.makeText(applicationContext,"Please enter a name please thank you very much please and thank you.",Toast.LENGTH_LONG).show()
                navBtn.visibility=View.INVISIBLE
            }
        }
        navBtn.setOnClickListener {
            val intent= Intent(this,SecondActivity::class.java)
            intent.putExtra("UserId", enteredname)
            startActivity(intent)
        }
    }
}