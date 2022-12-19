package com.example.movieapp.data.local.update.entity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UpMovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovies(movies:List<UpMovieEntity>)


    @Query("select * from upmovieentity limit (:page),(:offse)")
    suspend fun getAll(page:Int,offse:Int):List<UpMovieEntity>

    @Query("delete from upmovieentity where IMDBID in(:movies)")
    suspend fun deleteAll(movies: List<String>)

}