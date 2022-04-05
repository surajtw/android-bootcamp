package com.example.mysweetmovieapp.model

data class Movie(val id: Int) {
    var name: String = ""
    var image: String = ""
    var rating: Float = 0.0F

    constructor(id: Int, name: String, image: String, rating: Float) : this(id) {
        this.name = name;
        this.image = image;
        this.rating = rating;
    }

    constructor() : this(0)
}
