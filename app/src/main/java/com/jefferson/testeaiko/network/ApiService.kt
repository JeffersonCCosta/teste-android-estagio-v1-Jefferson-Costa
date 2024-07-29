package com.jefferson.testeaiko.network

import com.jefferson.testeaiko.model.YourModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("your_endpoint")
    fun getYourData(): Call<YourModel>
}