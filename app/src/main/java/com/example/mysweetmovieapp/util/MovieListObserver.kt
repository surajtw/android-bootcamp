package com.example.mysweetmovieapp.util

import com.example.mysweetmovieapp.model.Movie

interface MovieListObserver {
    fun onMovieListFetched(movieList: List<Movie>);
}