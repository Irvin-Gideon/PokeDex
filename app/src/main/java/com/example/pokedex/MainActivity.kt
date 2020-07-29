package com.example.pokedex

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

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
 * Post:  Parses through the JSON file to find the type in the JSON data
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





