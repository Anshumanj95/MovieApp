package com.example.movieapp.data.remote.dto

import com.example.movieapp.domain.model.Movies
import com.google.gson.annotations.SerializedName

data class MovieListDto(
    @SerializedName("Movie List")
    val Movie_List: List<MovieDto>
)

fun MovieListDto.toMovies():Movies{
    return Movies(Movie_List)
}