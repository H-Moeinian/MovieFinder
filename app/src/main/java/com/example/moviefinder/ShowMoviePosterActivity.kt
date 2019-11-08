package com.example.moviefinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_show_movie_poster.*


class ShowMoviePosterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_movie_poster)
        val posterURL = intent.getStringExtra("poster")
        Picasso.get().load(posterURL).into(this.imgPoster)


    }
}
