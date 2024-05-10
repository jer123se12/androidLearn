package com.seedling.demo.clapapp

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mediaPlayer=MediaPlayer.create(this,R.raw.wow)
        var btn=findViewById<Button>(R.id.www)
        btn.setOnClickListener {
            mediaPlayer.start()
        }
    }
}