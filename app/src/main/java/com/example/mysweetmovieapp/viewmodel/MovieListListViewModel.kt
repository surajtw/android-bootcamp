package com.example.mysweetmovieapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mysweetmovieapp.model.Movie
import com.example.mysweetmovieapp.repository.MovieListRepository
import com.example.mysweetmovieapp.util.MovieListObserver
import com.example.mysweetmovieapp.util.Resource

class MovieListListViewModel (private val movieRepository: MovieListRepository): ViewModel(), MovieListObserver {
    private val movies = MutableLiveData<Resource<List<Movie>>>();
    private val TAG = "MovieListListViewModel";

    init {
        movieRepository.movieListObserver = this;
        fetchMovies();
    }

    private fun fetchMovies () {
        Log.i(TAG, "fetchMovies");
        movies.postValue(Resource.loading(null));
        movieRepository.getMovies();
    }

    override fun onMovieListFetched(movieList: List<Movie>) {
        Log.i(TAG, "onMovieListFetched");
        movies.postValue(Resource.success(movieList));
    }

    fun getMovies(): LiveData<Resource<List<Movie>>> {
        Log.i(TAG, "getMovies in viewmodel");
        return movies
    }

    override fun onCleared() {
        super.onCleared();
        Log.i(TAG, "onCleared:: Viewmodel ready to be disposed");
    }
}