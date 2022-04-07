package com.example.mysweetmovieapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class ServerResponse (val data: Data) {}

data class Data(var cards: List<Movie>) {}

@Parcelize
data class Movie(var content: MovieMetaData): Parcelable

@Parcelize
data class MovieMetaData(var title: String, var movie_logo: String, var rating : String): Parcelable

