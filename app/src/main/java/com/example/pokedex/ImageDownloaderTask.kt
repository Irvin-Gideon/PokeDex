package com.example.pokedex

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.widget.Toast
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

/** AsyncTask is an Android API which executes a function on a background thread. In this instance
 * we will be using it to create a class which gets  an image from a website and stores it in a
 * Bitmap. From there we are able to use that image in in our main thread.
 */

class ImageDownloaderTask : AsyncTask<String, Void, Bitmap?>(){

    override fun doInBackground(vararg strings: String?): Bitmap? {
        try {
            val url = URL(strings[0])
            val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
            urlConnection.connect()
            val imageInput: InputStream = urlConnection.inputStream
            val myBitmap: Bitmap = BitmapFactory.decodeStream(imageInput)
            return myBitmap
        }catch(e: Exception){
            e.printStackTrace()

            return null
        }

    }

}