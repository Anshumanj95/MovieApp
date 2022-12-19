package com.example.movieapp.domain.repository

import com.example.movieapp.domain.model.Movie
import com.example.movieapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getMovie(page:Int,offse:Int):Resource<List<Movie>>

    suspend fun updateMovie(page:Int,offse:Int):Resource<List<Movie>>
}