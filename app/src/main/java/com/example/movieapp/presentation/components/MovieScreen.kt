package com.example.movieapp.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.presentation.MovieInfoViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun MovieScreen(
    viewModel: MovieInfoViewModel = hiltViewModel()
) {
    val scaffoldState= rememberScaffoldState()
    val movieList by remember {
        viewModel.movieList
    }
    val updateMovieList by remember {
        viewModel.updatedMovieList
    }
    val isLoading by remember {
        viewModel.isLoading
    }
    val isUpdated= mutableStateOf(false)
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            Card (elevation = 10.dp){
                Row(modifier = Modifier
                    .fillMaxWidth(),
                    verticalAlignment =CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Column(modifier = Modifier.fillMaxWidth(0.8f)) {
                        Text(text = "Movie App",
                            modifier = Modifier.align(CenterHorizontally),
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp)
                    }
                    IconButton(onClick = {
                        isUpdated.value=!isUpdated.value
                    }) {
                        Icon(Icons.Filled.Refresh, contentDescription = "null")
                    }
                }
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.surface)) {

            if (isLoading){
                CircularProgressIndicator(modifier = Modifier.align(Center))
            }

            if (isUpdated.value) {
                LazyColumn{
                    val itemCount=updateMovieList.size
                    items(itemCount){
                        if (it>=itemCount-1){
                            viewModel.upCurrPage++
                            viewModel.loadUpdateMoviePaginated()
                        }
                        MovieItem(movie =updateMovieList[it])
                    }
                }
            } else {
                LazyColumn{
                    val itemCount=movieList.size
                    items(itemCount){
                        if (it>=itemCount-1) {
                            viewModel.currPage++
                            viewModel.loadMoviePaginated()
                        }
                        MovieItem(movie =movieList[it])
                    }
                }
            }
        }

    }
}

