package com.example.mysweetmovieapp.viewModel

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mysweetmovieapp.MovieListActivity
import com.example.mysweetmovieapp.R
import com.example.mysweetmovieapp.adapter.MovieAdapter
import com.example.mysweetmovieapp.model.Movie
import com.example.mysweetmovieapp.repository.DataSource
import com.example.mysweetmovieapp.repository.DataSourceUpdate

class MovieViewModel : DataSourceUpdate {
    private lateinit var movieUpdatedList: List<Movie>
    var dataSource: DataSource = DataSource.getInstance()


    fun setDataSource():DataSource {
        dataSource.setObserver(this)
      return  dataSource
    }

    override fun remoteMovieListUpdated(remoteMovies: List<Movie>) {
        this.movieUpdatedList = remoteMovies
    }
}