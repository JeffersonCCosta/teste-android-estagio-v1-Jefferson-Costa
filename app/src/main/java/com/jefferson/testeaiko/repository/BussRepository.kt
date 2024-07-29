package com.jefferson.testeaiko.repository

import com.example.app_test.model.BussInfo
import com.jefferson.testeaiko.model.Location
import com.jefferson.testeaiko.network.ApiService
import com.jefferson.testeaiko.network.RetrofitClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BussRepository {
    private val apiService: ApiService = RetrofitClient.getClient("https://aiko-olhovivo-proxy.aikodigital.io/").create(ApiService::class.java)

    fun getYourData(text: String): Flow<List<BussInfo>?> {

        return flow {
            emit(apiService.getYourData(search = text))
        }
    }

    fun auth(): Flow<Boolean> {
        return flow {
            apiService.auth()
            emit(true)
        }
    }

    fun getParadas(text: String): Flow<List<Location>?> {
        return flow {
            emit(apiService.getParadas(search = text))
        }
    }
}