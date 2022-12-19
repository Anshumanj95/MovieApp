package com.example.movieapp.data.repository

import android.util.Log
import com.example.movieapp.data.local.entity.MovieDao
import com.example.movieapp.data.remote.MovieApi
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.util.Resource
import retrofit2.HttpException
import java.io.IOException
import com.example.movieapp.data.local.update.entity.UpMovieDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(
    private val api: MovieApi,
    private val dao:MovieDao,
    private val updao:UpMovieDao):MovieRepository {

    override suspend fun getMovie(page:Int,offse:Int): Resource<List<Movie>> {
        val movies=dao.getAll(page, offse).map {
            it.toMovieEntity()
        }

        try {
            val remoteMovie=api.getMovie()
            dao.deleteAll(remoteMovie.Movie_List.map { it.IMDBID })
            dao.insertMovies(remoteMovie.Movie_List.map { it.toMovieEntity() })
        }catch (e: HttpException){
            Log.e("Error","oops,something went wrong")
        }catch (e: IOException){
            Log.e("Error","Check your network Connection")
        }
        val newMovie=dao.getAll(page,offse).map { it.toMovieEntity() }
        return (Resource.Success(newMovie))
    }

    override suspend fun updateMovie(page:Int,offse:Int): Resource<List<Movie>> {
        val movies=updao.getAll(page,offse).map {
            it.toUpMovieEntity()
        }

        try {
            val remoteMovie=api.updateGetMovie()
            updao.deleteAll(remoteMovie.Movie_List.map { it.IMDBID })
            updao.insertMovies(remoteMovie.Movie_List.map { it.toUpMovieEntity() })
        }catch (e: HttpException){
            Log.e("Error","oops,something went wrong")
        }catch (e: IOException){
            Log.e("Error","Check your network Connection")
        }
        val newMovie=updao.getAll(page,offse).map { it.toUpMovieEntity() }
        return (Resource.Success(newMovie))
    }
}