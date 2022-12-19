package com.example.movieapp.data.remote.dto

import com.example.movieapp.data.local.entity.MovieEntity
import com.example.movieapp.data.local.update.entity.UpMovieEntity
import com.google.gson.annotations.SerializedName

data class MovieDto(
    val Cast: String,
    val Director: String,
    val Genres: String,
    val IMDBID: String,
    @SerializedName("Movie Poster")
    val Movie_Poster: String,
    val Rating: String,
    val Runtime: String,
    @SerializedName("Short Summary")
    val Short_Summary: String,
    val Summary: String,
    val Title: String,
    val Writers: String,
    val Year: String,
    @SerializedName("YouTube Trailer:")
    val YouTube_Trailer: String
){
    fun toMovieEntity(): MovieEntity {
        return MovieEntity(
            Cast = Cast,
            Director=Director,
            Genres=Genres,
            IMDBID=IMDBID,
            Movie_Poster=Movie_Poster,
            Rating=Rating,
            Runtime=Runtime,
            Summary=Summary,
            Title=Title,
            Year=Year
        )
    }
    fun toUpMovieEntity(): UpMovieEntity {
        return UpMovieEntity(
            Cast = Cast,
            Director=Director,
            Genres=Genres,
            IMDBID=IMDBID,
            Movie_Poster=Movie_Poster,
            Rating=Rating,
            Runtime=Runtime,
            Summary=Summary,
            Title=Title,
            Year=Year
        )
    }
}