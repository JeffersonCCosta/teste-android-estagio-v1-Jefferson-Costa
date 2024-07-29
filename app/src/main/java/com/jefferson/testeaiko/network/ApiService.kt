package com.jefferson.testeaiko.network

import com.example.app_test.model.BussInfo
import com.jefferson.testeaiko.model.Location
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("Linha/Buscar")
    suspend fun getYourData(@Query("token") token: String = "ac50b9c017f9406702faa73f055c895ddb5d02f3ff4f7d1265b5bd9c2837abbd",
        @Query("termosBusca") search: String = ""): List<BussInfo>

    @POST("Login/Autenticar")
    suspend fun auth(@Query("token") token: String = "ac50b9c017f9406702faa73f055c895ddb5d02f3ff4f7d1265b5bd9c2837abbd"): Unit


    @GET("Parada/BuscarParadasPorLinha")
    suspend fun getParadas(@Query("token") token: String = "ac50b9c017f9406702faa73f055c895ddb5d02f3ff4f7d1265b5bd9c2837abbd"): List<Location>

}