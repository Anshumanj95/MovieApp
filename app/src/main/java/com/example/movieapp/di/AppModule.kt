package com.example.movieapp.di

import android.app.Application
import androidx.room.Room
import com.example.movieapp.data.local.MovieDatabase
import com.example.movieapp.data.local.UpdateMovieDatabase
import com.example.movieapp.data.remote.MovieApi
import com.example.movieapp.data.repository.MovieRepositoryImpl
import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.util.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieApi():MovieApi{
        val interceptor: HttpLoggingInterceptor =HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        val client:OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieDatabase(app:Application):MovieDatabase{
        return Room.databaseBuilder(
            app,
            MovieDatabase::class.java,"movie_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUpdateMovieDatabase(app:Application):UpdateMovieDatabase{
        return Room.databaseBuilder(
            app,
            UpdateMovieDatabase::class.java,"up_movie_db"
        ).build()
    }
    @Provides
    @Singleton
    fun provideMovieRepository(db:MovieDatabase,udb:UpdateMovieDatabase,api:MovieApi):MovieRepository{
        return MovieRepositoryImpl(api,db.dao,udb.dao)
    }


}