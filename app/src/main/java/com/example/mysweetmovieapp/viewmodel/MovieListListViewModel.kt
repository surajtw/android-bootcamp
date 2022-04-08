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
        movies.postValue(Resource.loading(null));
        movieRepository.getMovies();
    }

    override fun onMovieListFetched(movieList: List<Movie>) {
        for (movie in movieList){
            var rating = movie.content.rating.toFloat();
            if(rating >= 7){
                movie.content.rating = "GOOD"
            }
            else{
                movie.content.rating = "BAD"
            }
        }
        movies.postValue(Resource.success(movieList));
    }

    fun getMovies(): LiveData<Resource<List<Movie>>> {
        return movies
    }

    override fun onCleared() {
        super.onCleared();
        Log.i(TAG, "onCleared:: Viewmodel ready to be disposed");
    }
}