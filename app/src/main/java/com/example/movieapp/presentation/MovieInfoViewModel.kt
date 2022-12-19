package com.example.movieapp.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieInfoViewModel @Inject constructor(
    private val repository: MovieRepository
):ViewModel(){

    var currPage=0;
    var upCurrPage=0;
    var movieList= mutableStateOf<List<Movie>>(listOf())
    var isLoading= mutableStateOf(false)
    var updatedMovieList= mutableStateOf<List<Movie>>(listOf())
    init {
        loadMoviePaginated()
        loadUpdateMoviePaginated()
    }

    fun loadMoviePaginated(){
        viewModelScope.launch {
            val result=repository.getMovie(currPage*10,(10*(currPage+1)))
                when (result) {
                    is Resource.Success -> {
                        movieList.value+= result.data?: emptyList()
                        isLoading.value = false
                    }
                    is Resource.Error -> {
                        movieList.value+= result.data?: emptyList()
                        isLoading.value = false
                    }
                    is Resource.Loading -> {
                        movieList.value+= result.data?: emptyList()
                        isLoading.value = true
                    }
                }
        }
    }

    fun loadUpdateMoviePaginated(){
        viewModelScope.launch {
            isLoading.value=true
            val result=repository.updateMovie(upCurrPage*10,(10*(upCurrPage+1)))
            when (result) {
                is Resource.Success -> {
                    isLoading.value = false
                    updatedMovieList.value+=result.data?: emptyList()
                }
                is Resource.Error -> {
                    updatedMovieList.value = result.data ?: emptyList()
                    isLoading.value = false
                }
                is Resource.Loading -> {
                    isLoading.value = true
                }
            }

        }
    }


}