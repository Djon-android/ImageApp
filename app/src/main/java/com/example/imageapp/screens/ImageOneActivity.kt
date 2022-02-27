package com.example.imageapp.screens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.imageapp.R
import com.squareup.picasso.Picasso

class ImageOneActivity : AppCompatActivity() {

    private lateinit var imageViewOne: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_image)
        imageViewOne = findViewById(R.id.imageViewOne)

        if (intent == null || !intent.hasExtra(IMAGE_URL)) {
            finish()
            return
        }
        val urlImage = intent.getStringExtra(IMAGE_URL)
        Picasso.get().load(urlImage).error(R.drawable.ic_baseline_thumb_up_24).into(imageViewOne)
    }

    companion object {

        private const val IMAGE_URL: String = "image"

        fun newIntent(context: Context, imageUrl: String): Intent {
            val intent = Intent(context, ImageOneActivity::class.java)
            intent.putExtra(IMAGE_URL, imageUrl)
            return intent
        }
    }
}