package com.seedling.demo.recyclethis

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    val texts= listOf<Fruit>(Fruit("apple", "professor oak"),Fruit("pear", "your mom"), Fruit("chicken", "eggy van laid"))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rv=findViewById<RecyclerView>(R.id.MYRC)
        rv.setBackgroundColor(Color.BLACK)
        rv.layoutManager=LinearLayoutManager(this)
        rv.adapter=MyrvAdapter(texts){selectedItem:Fruit -> listItemc(selectedItem)}
    }
    private fun listItemc(fruit: Fruit){
        Toast.makeText(this@MainActivity,"supplier fruit: ${fruit.supplier}", Toast.LENGTH_LONG).show()
    }
}