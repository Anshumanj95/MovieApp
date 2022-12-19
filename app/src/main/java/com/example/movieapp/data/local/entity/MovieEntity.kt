package com.example.movieapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movieapp.domain.model.Movie

@Entity
data class MovieEntity(
    val Cast: String,
    val Director: String,
    val Genres: String,
    @PrimaryKey val IMDBID: String,
    val Movie_Poster: String,
    val Rating: String,
    val Runtime: String,
    val Summary: String,
    val Title: String,
    val Year: String,
){
    fun toMovieEntity():Movie{
        return Movie(
            Cast,
            Director,
            Genres,
            IMDBID,
            Movie_Poster,
            Rating,
            Runtime,
            Summary,
            Title,
            Year)
    }
}