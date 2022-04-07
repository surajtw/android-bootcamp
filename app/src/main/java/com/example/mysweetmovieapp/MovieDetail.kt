package com.example.mysweetmovieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mysweetmovieapp.model.Movie

class MovieDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        val movie = intent.getParcelableExtra<Movie>("movie")
        if (movie != null) {
            Toast.makeText(this, movie.content.title, Toast.LENGTH_SHORT).show()
        };
    }
}