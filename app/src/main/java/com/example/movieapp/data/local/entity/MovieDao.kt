package com.example.movieapp.data.local.entity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovies(movies:List<MovieEntity>)


    @Query("select * from movieentity limit (:page),(:offse)")
    suspend fun getAll(page:Int,offse:Int):List<MovieEntity>

    @Query("delete from movieentity where IMDBID in(:movies)")
    suspend fun deleteAll(movies: List<String>)

}