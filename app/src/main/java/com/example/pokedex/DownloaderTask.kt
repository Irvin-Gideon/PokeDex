package com.example.pokedex


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
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

/**
 * Pre: 'string' must be a link to a website in plain text
 * Post: Returns the HTML information from the url in the form of a string
**/
 suspend fun downloaderTask (string: String) : String{

        var file = ""

            try {

                val url = URL(string) //converts string input to a URL Object
                val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
                val input: InputStream = urlConnection.inputStream
                val inputRead = InputStreamReader(input)

                var data: Int = inputRead.read()
                var ch: Char
                while (data != -1) // Loop which writes (character by character) website data to a string
                {
                    ch = data.toChar()
                    file += ch
                    data = inputRead.read()
                }

                return file
            } catch (e: Exception) {
                e.printStackTrace()
                return "Error"
            }

    }

    /** Helper function used to parse through a JSON Object to find required information

     * Pre: 's' must be a string containing JSON data
     * Post: Returns a specified array or string of data from the JSON file
     */
    fun getJSONInfo (s: String, info: String): String? {
        try {
            val file = JSONObject(s)

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }


