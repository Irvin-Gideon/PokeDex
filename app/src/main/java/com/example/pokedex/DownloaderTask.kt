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
 **/


/**
 * Pre: 'string' must be a link to a website in plain text
 * Post: Returns the HTML information from the url in the form of a string
**/
 suspend fun downloaderTask (link: String) : String{
    return withContext(Dispatchers.IO) {
            try {
                  return@withContext URL(link).readText()
            } catch (e: Exception) {
                e.printStackTrace()
                return@withContext "Error"
            }
        }
    }

/** Helper function used to parse through a string to find required information
 * and convert it to a JSON Object
 *
 * Pre: 's' must be a string containing JSON data
 * Post: Returns a specified JSON object of data from the  file
 */
suspend fun parseJSONObj (s: String?, info: String): String? {
    return withContext(Dispatchers.Default) {// Heavy tasks like parsing through JSON are best handled in the default dispatching thread
        try {
            if (s.equals(null)) {
                return@withContext null
            }
            val file = JSONObject(s)
            return@withContext file.getString(info)

        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext null
        }
    }
}

/** Helper function used to parse through a string to find required information and convert it
 * to a JSON Array

 * Pre: 's' must be a string containing JSON data
 * Post: Returns a specified JSON array of data from the file
 */
suspend fun parseJSONArr (s: String?, index: Int): String? {
    return withContext(Dispatchers.Default) {// Heavy tasks like parsing through JSON are best handled in the default dispatching thread
        try {
            if (s.equals(null)) {
                return@withContext null
            }
            val file = JSONArray(s)
            return@withContext file.get(index).toString()

        } catch (e: Exception) {
            //e.printStackTrace()
            return@withContext null
        }
    }
}




