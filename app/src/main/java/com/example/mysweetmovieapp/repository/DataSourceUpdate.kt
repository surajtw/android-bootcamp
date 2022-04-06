package com.example.mysweetmovieapp.repository

import com.example.mysweetmovieapp.model.Movie

interface DataSourceUpdate {
    fun remoteMovieListUpdated(remoteMovies: List<Movie>)
}