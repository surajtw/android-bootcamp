package com.example.mysweetmovieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mysweetmovieapp.adapter.MovieAdapter
import com.example.mysweetmovieapp.model.Movie
import com.example.mysweetmovieapp.repository.DataSource
import com.example.mysweetmovieapp.repository.DataSourceUpdate
import com.example.mysweetmovieapp.viewModel.MovieViewModel

class MovieListActivity : AppCompatActivity(){
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var movieRecyclerViewAdapter: MovieAdapter
    val movieViewModel:MovieViewModel= MovieViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        val movieRV = findViewById<RecyclerView>(R.id.movie_list_view);
        layoutManager = LinearLayoutManager(this)
        movieRV.layoutManager = layoutManager
        val dataSource=movieViewModel.setDataSource()
        movieRecyclerViewAdapter = MovieAdapter(this, dataSource.remoteMovies as ArrayList<Movie>);
        movieRecyclerViewAdapter.setMovieListItems(dataSource.remoteMovies)
        movieViewModel.remoteMovieListUpdated(dataSource.remoteMovies)
        movieRV.adapter = movieRecyclerViewAdapter
    }


    }

