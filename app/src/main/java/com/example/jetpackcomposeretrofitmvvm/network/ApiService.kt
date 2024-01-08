package com.example.jetpackcomposeretrofitmvvm.network

import com.example.jetpackcomposeretrofitmvvm.model.Movie
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET


interface ApiService {
    /**
     * here the movielist.json is the file that is sitting on a server and contains a list of json objects as below
     * {
     * "category": "Latest",
     * "imageUrl": "https://howtodoandroid.com/images/coco.jpg",
     * "name": "Coco",
     * "desc": "Coco is a 2017 American 3D computer-animated musical fantasy adventure film produced by Pixar"
     * },
     *
     * To get this data into a class we have created the Movie data class
      */

    @GET("movielist.json")
    suspend fun getMovies(): List<Movie>

    /**
     * Companion object in kotlin - similar to static methods and properties in java
     * with out creating an instance of this class you can call the methods and properties of this class
     */
    companion object{
        var apiService: ApiService? = null
        fun getInstance(): ApiService{
            if (apiService == null){
                apiService = Retrofit.Builder()
                    .baseUrl("https://howtodoandroid.com/apis/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create()
            }
            return apiService!!
        }
    }
}