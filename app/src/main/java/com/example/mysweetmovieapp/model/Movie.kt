package com.example.mysweetmovieapp.model

data class ServerResponse(val data: Data) {}
data class Data(var cards: List<Movie>) {}
data class Movie(var content: MovieMetaData) {}
data class MovieMetaData(var title: String, var movie_logo: String, var rating: String) {}

