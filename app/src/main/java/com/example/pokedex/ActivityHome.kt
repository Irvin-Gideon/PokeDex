package com.example.pokedex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class ActivityHome : AppCompatActivity() {
    private var SPLASH_TIME = 7000 //This is 3 seconds
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//
//        Handler().postDelayed({ //Do any action here. Now we are moving to next page
//            val mySuperIntent = Intent(this, MainActivity::class.java)
//            startActivity(mySuperIntent)
//
//            //This 'finish()' is for exiting the app when back button pressed from Home page which is ActivityHome
//            finish()
//        }, SPLASH_TIME.toLong())

    }
}