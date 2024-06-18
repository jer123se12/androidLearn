package com.seedling.demo.recyclethis

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    var texts= listOf<Fruit>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val str = assets.open("Fruits").bufferedReader().use { it.readText() }
        Log.i("mylog", str)
        val answer = JSONObject(str)
        val lis=answer.getJSONArray("Items")
        for (i in 0 until lis.length()) {
            texts+=Fruit(lis.getJSONObject(i).getString("Fruitname"),lis.getJSONObject(i).getString("supplier"))
        }
        val rv=findViewById<RecyclerView>(R.id.MYRC)
        rv.setBackgroundColor(Color.BLACK)
        rv.layoutManager=LinearLayoutManager(this)
        rv.adapter=MyrvAdapter(texts){selectedItem:Fruit -> listItemc(selectedItem)}
    }
    private fun listItemc(fruit: Fruit){
        Toast.makeText(this@MainActivity,"supplier fruit: ${fruit.supplier}", Toast.LENGTH_LONG).show()
    }
}