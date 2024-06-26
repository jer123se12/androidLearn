package com.seedling.demo.spdemo

import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.util.Log

class MainActivity : AppCompatActivity() {
    private lateinit var nameText:EditText
    private lateinit var ageText:EditText
    private lateinit var sf:SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameText=findViewById(R.id.NameField)
        ageText=findViewById(R.id.NumberField)
        sf=getSharedPreferences("my_sf", MODE_PRIVATE)
        editor=sf.edit()


    }
    override fun onPause(){
        super.onPause()
        val name=nameText.text.toString()
        val age=ageText.text.toString().toInt()
        editor.apply(){
            putString("sf_name",name)
            putInt("sf_age", age)
            commit()
        }
    }

    override fun onResume() {
        super.onResume()
        Log.i("MyTag", "on resume")
        val name=sf.getString("sf_name","")
        val age=sf.getInt("sf_age",0)
        Log.i("MyTag", "here")
        nameText.setText(name)
        Log.i("MyTag", "here")
        if (age!=0) {
            ageText.setText(age.toString())
        }
        Log.i("MyTag", "here")

    }
}