package com.example.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ActivityHome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val tvWelcome : TextView = findViewById(R.id.tvWelcome)
       // tvWelcome.animate().rotationBy(1440F).duration = 300
    }
}