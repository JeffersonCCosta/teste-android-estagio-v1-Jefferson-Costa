package com.jefferson.testeaiko.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jefferson.testeaiko.model.YourModel
import com.jefferson.testeaiko.network.ApiService
import com.jefferson.testeaiko.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YourRepository {
    private val apiService: ApiService = RetrofitClient.getClient("https://api.yourservice.com/").create(ApiService::class.java)

    fun getYourData(): LiveData<YourModel?> {
        val data = MutableLiveData<YourModel?>()
        apiService.getYourData().enqueue(object : Callback<YourModel> {
            override fun onResponse(call: Call<YourModel>, response: Response<YourModel>) {
                if (response.isSuccessful) {
                    data.value = response.body()
                }
            }

            override fun onFailure(call: Call<YourModel>, t: Throwable) {
                data.value = null
            }
        })
        return data
    }
}