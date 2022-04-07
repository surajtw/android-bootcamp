package com.example.mysweetmovieapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mysweetmovieapp.adapter.MovieAdapter
import com.example.mysweetmovieapp.model.Movie
import com.example.mysweetmovieapp.repository.DataSource
import com.example.mysweetmovieapp.repository.DataSourceUpdate

class MovieListActivity : AppCompatActivity(), DataSourceUpdate {
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var movieRecyclerViewAdapter: MovieAdapter

    val dataSource = DataSource.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        dataSource.setObserver(this)

        val movieRV = findViewById<RecyclerView>(R.id.movie_list_view);
        layoutManager = LinearLayoutManager(this)
        movieRV.layoutManager = layoutManager
        movieRecyclerViewAdapter = MovieAdapter(this, dataSource.remoteMovies as ArrayList<Movie>);
        movieRV.adapter = movieRecyclerViewAdapter

        val intent = Intent(this, MovieDetail::class.java)

    }

    override fun remoteMovieListUpdated(remoteMovies: List<Movie>) {
        movieRecyclerViewAdapter.setMovieListItems(remoteMovies)
    }
}