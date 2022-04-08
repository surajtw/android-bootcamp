package com.example.mysweetmovieapp.repository

import android.util.Log
import com.example.mysweetmovieapp.api.MovieService
import com.example.mysweetmovieapp.model.Movie
import com.example.mysweetmovieapp.util.MovieListObserver

class MovieListRepository (private val movieService: MovieService) : MovieListObserver {
     var movieListObserver: MovieListObserver? = null
    private val TAG = "MovieListRepository"
    init {
        Log.i(TAG, "Repo init!");
        movieService.setMovieObserver(this);
    }

    fun getMovies(): List<Movie> {
        Log.i(TAG, "Repo get movies!");
        return movieService.remoteMovies;
    }

    override fun onMovieListFetched(movieList: List<Movie>) {
        movieListObserver?.onMovieListFetched(movieList);
        Log.i(TAG, "Repo onMovieListFetched!");
    }
}