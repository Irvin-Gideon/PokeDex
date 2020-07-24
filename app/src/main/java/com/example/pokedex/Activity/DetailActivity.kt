package com.example.pokedex.Activity

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.pokedex.R

class DetailActivity : AppCompatActivity() {
    private lateinit var mImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_pokemon_info_display)



        mImageView = findViewById(R.id.pokemonInfoImage)

        val mBundle = intent.extras
        if (mBundle != null) {
            mImageView.setImageBitmap(mBundle["pokemonSprite"] as Bitmap?)
        }

    }


}