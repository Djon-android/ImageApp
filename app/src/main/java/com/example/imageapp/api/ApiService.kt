package com.example.imageapp.api

import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("list.php")
    fun getAllImage(): Single<MutableList<String>>
}