package com.example.mysweetmovieapp.repository

import android.util.Log
import com.example.mysweetmovieapp.api.MovieService
import com.example.mysweetmovieapp.model.Movie
import com.example.mysweetmovieapp.util.MovieListObserver

class MovieListRepository (private val movieService: MovieService) : MovieListObserver {
     var movieListObserver: MovieListObserver? = null

    init {
        movieService.setMovieObserver(this);
    }

    fun getMovies(): List<Movie> {
        return movieService.remoteMovies;
    }

    override fun onMovieListFetched(movieList: List<Movie>) {
        Log.d("from","MovieListRepo listfetch method")
        movieListObserver?.onMovieListFetched(movieList);
    }
}