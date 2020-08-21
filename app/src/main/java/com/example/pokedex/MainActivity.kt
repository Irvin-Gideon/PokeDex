package com.example.pokedex

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pokedex.Activity.DataBaseHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

var pokeTestListSize = 807
val pokeTestList = ArrayList<ReworkedPokemonItem>(pokeTestListSize)
lateinit var dataBaseHelper: DataBaseHelper

class MainActivity : AppCompatActivity() {
    private var SPLASH_TIME = 5000 //This is 5 seconds

    private var imageView : ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


         dataBaseHelper= DataBaseHelper(this)
        CoroutineScope(Dispatchers.Main).launch {
            if (!dataBaseHelper.tableExists()) {
                dataBaseHelper.addAll()
                SPLASH_TIME = 10000
                Log.i("info", "don't exist")
            } else {
                SPLASH_TIME = 3000
            }
            val everyone: ArrayList<ReworkedPokemonItem> = dataBaseHelper.getEveryOne()
            pokeTestList.addAll(everyone)




            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT


            window.setBackgroundDrawable(null)
            initializeView()
            animateLogo()
            goToMainActivity()


        }
    }

    private fun initializeView()
    {
        imageView = findViewById(R.id.splashLogo)
    }


    private fun animateLogo(){

        imageView?.alpha=0F
        imageView?.animate()?.rotationBy(1440F)?.duration=SPLASH_TIME/4L
        imageView?.animate()?.alpha(1F)?.duration=SPLASH_TIME.toLong()
    }

    private fun goToMainActivity()
    {
        Handler().postDelayed({ //Do any action here. Now we are moving to next page
            val mySuperIntent = Intent(this, ActivityHome::class.java)
            startActivity(mySuperIntent)


            //This 'finish()' is for exiting the app when back button pressed from Home page which is ActivityHome
            finish()
        }, SPLASH_TIME.toLong())

    }
}



/**Pre: oldString must be a string in all lowerCase
 * Post:  Returns newstring with the first letter capitalized
 */

fun cap( oldString: String?): String?{
    if(oldString==null) return null
    if(oldString[0].isUpperCase()) return oldString

    var newString=""
    newString+= oldString[0].toUpperCase()
    for(i in 1 until oldString.length){
        newString+=oldString[i]
    }
    return newString
}





