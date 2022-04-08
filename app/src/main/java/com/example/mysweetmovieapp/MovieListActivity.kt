package com.example.mysweetmovieapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.LayoutDirection
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.mysweetmovieapp.adapter.MovieAdapter
import com.example.mysweetmovieapp.model.Movie
import com.example.mysweetmovieapp.viewmodel.MovieListListViewModel

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysweetmovieapp.api.MovieService
import com.example.mysweetmovieapp.repository.MovieListRepository
import com.example.mysweetmovieapp.util.Status
import kotlinx.android.synthetic.main.activity_movie_list.*

class MovieListActivity : AppCompatActivity() {
    private val TAG = "MovieListActivity"

    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var movieRecyclerViewAdapter: MovieAdapter

    private lateinit var movieViewModel: MovieListListViewModel;
    private lateinit var movieListRepository: MovieListRepository;
    private lateinit var movieRV: RecyclerView;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        setupViewModel();
        setupObserver();
        movieRV = findViewById<RecyclerView>(R.id.movie_list_view);
        val loadingText = findViewById<TextView>(R.id.loadingText);
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
                    loadingText.visibility = View.GONE
                    movieRV.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    Log.i(TAG, "Data loading...");
                    loadingText.visibility = View.VISIBLE
                    movieRV.visibility = View.INVISIBLE
                    loadingText.text = "Loading Data"
                }
                else -> {
                    Log.e(TAG, "${it.message}")
                }
            }
        })
    }

    private fun updateList(movies: List<Movie>) {
        Log.i(TAG, "update list in recycler view");
        movieRecyclerViewAdapter.setMovieListItems(movies)
    }

    private fun setupViewModel() {
        Log.i(TAG, "set up viewmodel");
        movieListRepository = MovieListRepository(MovieService());
        movieViewModel = ViewModelProvider(this, ViewModelFactory(movieListRepository)). get(MovieListListViewModel::class.java)
    }

    private class ViewModelFactory(val movieListRepository: MovieListRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MovieListListViewModel(movieListRepository) as T
        }
    }

}