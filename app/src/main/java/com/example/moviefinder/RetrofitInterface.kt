package com.example.moviefinder

import com.example.moviefinder.MovieDatabase.MovieClass
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("?apikey=70ad462a")
    fun getRelatedMovies(@Query("s") s: String): Observable<MovieClass>


}