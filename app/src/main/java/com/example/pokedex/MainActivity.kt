package com.example.pokedex

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var SPLASH_TIME = 3000 //This is 3 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        Handler().postDelayed({ //Do any action here. Now we are moving to next page
            val mySuperIntent = Intent(this, ActivityHome::class.java)
            startActivity(mySuperIntent)

            //This 'finish()' is for exiting the app when back button pressed from Home page which is ActivityHome
            finish()
        }, SPLASH_TIME.toLong())



    }


}

private fun <E> MutableList<E>.addAll(elements: MutableList<E?>) {
    for(n in elements){
        if (n != null) {
            add(n)
        }
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





