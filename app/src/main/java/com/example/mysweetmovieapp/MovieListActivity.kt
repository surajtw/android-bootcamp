package com.example.mysweetmovieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.mysweetmovieapp.adapter.MovieAdapter
import com.example.mysweetmovieapp.model.Movie
import com.example.mysweetmovieapp.viewmodel.MovieListViewModel

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysweetmovieapp.api.MovieService
import com.example.mysweetmovieapp.repository.MovieListRepository
import com.example.mysweetmovieapp.util.Status

class MovieListActivity : AppCompatActivity() {
    private val TAG = "MovieListActivity"

    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var movieRecyclerViewAdapter: MovieAdapter

    private lateinit var movieViewModel: MovieListViewModel;
    private lateinit var movieListRepository: MovieListRepository;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        setupViewModel();
        setupObserver();
        val movieRV = findViewById<RecyclerView>(R.id.movie_list_view);
        layoutManager = LinearLayoutManager(this)
        movieRV.layoutManager = layoutManager
        movieRecyclerViewAdapter = MovieAdapter(this, arrayListOf());
        movieRV.adapter = movieRecyclerViewAdapter
    }


    private fun setupObserver() {
        movieViewModel.getMovies().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    Log.i(TAG, "Data loaded!");
                    it.data?.let { movies -> updateList(movies) }
                }
                Status.LOADING -> {
                    Log.i(TAG, "Data loading...");
                }
                else -> {
                    Log.e(TAG, "${it.message}")
                }
            }
        })
    }

    private fun updateList(movies: List<Movie>) {
        movieRecyclerViewAdapter.setMovieListItems(movies)
    }

    private fun setupViewModel() {
        movieListRepository = MovieListRepository(MovieService());
        movieViewModel = ViewModelProvider(this, ViewModelFactory(movieListRepository)). get(MovieListViewModel::class.java)
    }

    private class ViewModelFactory(val movieListRepository: MovieListRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MovieListViewModel(movieListRepository) as T
        }
    }

}