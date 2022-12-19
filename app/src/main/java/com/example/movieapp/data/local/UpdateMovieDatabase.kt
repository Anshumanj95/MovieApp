package com.example.movieapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieapp.data.local.update.entity.UpMovieDao
import com.example.movieapp.data.local.update.entity.UpMovieEntity

@Database(
    entities = [UpMovieEntity::class],
    version = 1
)
abstract class UpdateMovieDatabase: RoomDatabase() {
    abstract val dao: UpMovieDao
}