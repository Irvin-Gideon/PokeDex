package com.example.pokedex

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

/** Using Kotlin Coroutines to execute a function on a background thread. In this instance
 * we will be using it to create a class which gets  an image from a website and stores it in a
 * Bitmap. From there we are able to use that image in in our main thread.
 */

/**
 * Pre: string must be a link to a website in plain text
 * Post: Returns the image stored at the link given in a bitmap file format
 **/

suspend fun imageDownloaderTask(s: String): Bitmap? {
    return withContext(Dispatchers.IO) {
        try {
            val url = URL(s) // converts string input to a URL Object
            val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
            urlConnection.connect() // connects to URL given
            val imageInput: InputStream = urlConnection.inputStream
            return@withContext BitmapFactory.decodeStream(imageInput) //converts information from website into bitmap file
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext null
        }

    }
}
