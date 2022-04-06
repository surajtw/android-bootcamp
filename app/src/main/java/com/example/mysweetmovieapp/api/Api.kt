package com.example.mysweetmovieapp.api

import com.example.mysweetmovieapp.model.ServerResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface Api {
    @GET("interview_ios.json")
    fun getMovies(): Call<ServerResponse>

    companion object {

        var BASE_URL = "https://tw-mobile-hiring.web.app/"

        fun create() : Api {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(Api::class.java)

        }
    }
}