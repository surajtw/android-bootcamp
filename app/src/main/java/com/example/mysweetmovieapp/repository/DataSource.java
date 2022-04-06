package com.example.mysweetmovieapp.repository;

import android.util.Log;

import com.example.mysweetmovieapp.api.Api;
import com.example.mysweetmovieapp.model.Movie;
import com.example.mysweetmovieapp.model.ServerResponse;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataSource {
    private static DataSource instance = null;
    private List<Movie> remoteMovies = new ArrayList<Movie>();
    private DataSourceUpdate observer = null;

    public List<Movie> getRemoteMovies() {
        Call<ServerResponse> movies = Api.Companion.create().getMovies();

        movies.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                Log.e("Response", "${response.body()} = " + response.body());
                remoteMovies = response.body().getData().getCards();
                if(observer != null) {
                    observer.remoteMovieListUpdated(remoteMovies);
                }

            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.e("Response", "Failed to load data " + t.getMessage());
            }
        });
        return remoteMovies;
    }

    public static DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }

    public void setObserver(@NotNull DataSourceUpdate observer) {
        this.observer = observer;
    }
}
