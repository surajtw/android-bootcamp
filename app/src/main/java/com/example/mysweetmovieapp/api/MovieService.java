package com.example.mysweetmovieapp.api;

import android.util.Log;

import com.example.mysweetmovieapp.model.Movie;
import com.example.mysweetmovieapp.model.ServerResponse;
import com.example.mysweetmovieapp.util.MovieListObserver;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieService {
    private List<Movie> remoteMovies = new ArrayList<Movie>();
    private MovieListObserver movieListObserver;
    public List<Movie> getRemoteMovies() {
        Call<ServerResponse> movies = Api.Companion.create().getMovies();

        movies.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                Log.e("Response", "${response.body()} = " + response.body());
                remoteMovies = response.body().getData().getCards();
                movieListObserver.onMovieListFetched(remoteMovies);
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.e("Response", "Failed to load data " + t.getMessage());
            }
        });
        return remoteMovies;
    }

    public void setMovieObserver(MovieListObserver movieListObserver) {
        this.movieListObserver = movieListObserver;
    }
}
