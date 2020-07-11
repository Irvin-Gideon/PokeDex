package com.example.pokedex


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

import org.json.JSONObject


/** Using Kotlin Coroutines to execute a function on a background thread. In this instance
 * we will be using it to create a class which gets the website information.
 * This downloader will execute an API call to an external website. From there we download the
 * JSON data and manipulate in our main thread.
 */

 suspend fun downloaderTask (string: String) : String{
    return withContext(Dispatchers.IO) {
        var file = ""

        try {

            val url = URL(string)
            val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
            val input: InputStream = urlConnection.inputStream
            val inputRead = InputStreamReader(input)

            var data: Int = inputRead.read()
            while (data != -1) // Loop which writes (character by character) website data to a string
            {
                val ch: Char = data.toChar()
                file += ch
                data = inputRead.read()
            }
            return@withContext file
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext "Error"
        }
    }
    }

    /**
     * Helper function used to parse through a JSON Object to find required information
     */
    fun getJSONInfo (s: String, info: String): String {
        val title = ""
        try {
            val file = JSONObject(s)

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return title
    }


