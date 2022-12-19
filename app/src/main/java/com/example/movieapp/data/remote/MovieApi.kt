package com.example.movieapp.data.remote

import com.example.movieapp.data.remote.dto.MovieDto
import com.example.movieapp.data.remote.dto.MovieListDto
import retrofit2.http.GET

interface MovieApi {

    @GET("1.json")
    suspend fun getMovie():MovieListDto

    @GET("2.json")
    suspend fun updateGetMovie():MovieListDto
}