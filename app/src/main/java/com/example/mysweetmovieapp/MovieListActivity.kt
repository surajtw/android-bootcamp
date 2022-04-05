package com.example.mysweetmovieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mysweetmovieapp.adapter.MovieAdapter
import com.example.mysweetmovieapp.model.Movie
import com.example.mysweetmovieapp.repository.DataSource

class MovieListActivity : AppCompatActivity() {
    private lateinit var layoutManager: RecyclerView.LayoutManager
    val dataSource = DataSource.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        val movieRV = findViewById<RecyclerView>(R.id.movie_list_view);
        layoutManager = LinearLayoutManager(this)
        movieRV.layoutManager = layoutManager
        movieRV.adapter = MovieAdapter(this, dataSource.localMovies as ArrayList<Movie>);
    }
}