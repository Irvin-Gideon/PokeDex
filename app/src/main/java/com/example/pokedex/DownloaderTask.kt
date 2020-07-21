package com.example.pokedex
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.net.URL

import org.json.JSONObject


/** Using Kotlin Coroutines to execute a function on a background thread. In this instance
 * we will be using it to create a class which gets the website information.
 * This downloader will execute an API call to an external website. From there we download the
 * JSON data and manipulate in our main thread.
 */

/**
 * Pre: 'string' must be a link to a website in plain text
 * Post: Returns the HTML information from the url in the form of a string
**/
 suspend fun downloaderTask (string: String) : String{
    return withContext(Dispatchers.IO) {
            try {
                  return@withContext URL(string).readText()
            } catch (e: Exception) {
                e.printStackTrace()
                return@withContext "Error"
            }
        }
    }

/** Helper function used to parse through a string to find required information
 * and convert it to a JSON Object
 * Pre: 's' must be a string containing JSON data
 * Post: Returns a specified JSON object of data from the  file
 */
fun getJSONObj (s: String, info: String): String? {
    return try {
        val file =JSONObject(s)
        file.getString(info)

    } catch (e: Exception) {
        e.printStackTrace()
        null

    }
}

/** Helper function used to parse through a string to find required information and convert it
 * to a JSON Array

 * Pre: 's' must be a string containing JSON data
 * Post: Returns a specified JSON array of data from the file
 */
fun getJSONArr (s: String): String? {
    return try{
        val file = JSONArray(s)
        return file.toString()

    } catch (e: Exception) {
        e.printStackTrace()
        null
    }

}


