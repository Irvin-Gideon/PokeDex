package com.example.pokedex.Activity

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.pokedex.R

class DetailActivity : AppCompatActivity() {
    private lateinit var mImageView: ImageView
    private lateinit var mTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_pokemon_info_display)



        mImageView = findViewById(R.id.pokemonInfoImage)
        mTextView = findViewById(R.id.pokemonInfoType)
        val mBundle = intent.extras //Get the attached bundle from the intent

        if (mBundle != null) {
            mImageView.setImageBitmap(mBundle["pokemonSprite"] as Bitmap?)
            mTextView.text = mBundle["pokemonType"] as String
        }

    }


}