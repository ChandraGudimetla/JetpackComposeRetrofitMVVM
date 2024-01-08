package com.example.jetpackcomposeretrofitmvvm.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposeretrofitmvvm.model.Movie
import com.example.jetpackcomposeretrofitmvvm.network.ApiService
import kotlinx.coroutines.launch
import java.lang.Exception

class MovieViewModel: ViewModel() {

    /**
     * below is the syntax for a mutable state backed property in jetpack compose
     *
     * by - this keyword is used for delegation in kotlin which states that the movieListResponse property
     * delegates its getter and setter property to another object (mutableStateOf(listOf()))
     *
     * --> mutableStateOf is a function provided by jetpack compose that holds a value
     * mutable state means something that can change at runtime
     */
    var movieListResponse: List<Movie> by mutableStateOf(listOf())
    var errorResponse: String by mutableStateOf("")


    /**
     * we will now call the api service in the view model
     *
     * viewmodelscope.launch - is the coroutine management within the lifecycle of viewmodel
     * what this means is that any coroutines that are created with in this scope will be cleared when the viewmodel is cleared (activity or fragment is destroyed)
     *
     * launch - is a coroutine builder function to launch a new coroutine, basically we are creating a new coroutine
     * with in the scope of viewmodel
     *
     * coroutines are light weight threads that run asynchronously can be used for fetching data from network or getting data from database
     * with out blocking the main thread
     *
     * coroutine scope - is used to manage when a coroutine should run and canceled
     * types: GlobalScope - for entire application, lifeCycleScope - for life cycle of an android life cycle owner (activity, fragment)
     *
     * using this scope helps in using resources efficiently and helps in preventing memory leaks
     */
    fun getMovieList(){
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try {
                val movieList = apiService.getMovies()
                movieListResponse = movieList
            }catch (e:Exception){
                errorResponse = e.message.toString()
            }
        }
    }
}