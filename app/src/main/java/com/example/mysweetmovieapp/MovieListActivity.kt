package com.example.mysweetmovieapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MovieListActivity : AppCompatActivity() {
    private val TAG = "MovieListActivity"

    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var movieRecyclerViewAdapter: MovieAdapter

    private lateinit var movieViewModel: MovieListListViewModel;
    private lateinit var movieListRepository: MovieListRepository;
    private lateinit var loadingTextView : TextView
    private lateinit var movieRV : RecyclerView
    val scope = CoroutineScope(Dispatchers.Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        setupViewModel();
        setupObserver();
        movieRV = findViewById(R.id.movie_list_view);
        loadingTextView = findViewById(R.id.loading_text)
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
                    scope.launch {
                        delay(3000L)
                        loadingTextView.visibility = View.GONE
                        movieRV.visibility = View.VISIBLE
                    }
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
        movieViewModel = ViewModelProvider(this, ViewModelFactory(movieListRepository)). get(MovieListListViewModel::class.java)
    }

    private class ViewModelFactory(val movieListRepository: MovieListRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MovieListListViewModel(movieListRepository) as T
        }
    }

}