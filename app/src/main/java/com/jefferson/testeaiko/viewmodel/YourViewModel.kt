package com.jefferson.testeaiko.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jefferson.testeaiko.model.YourModel
import com.jefferson.testeaiko.repository.YourRepository

class YourViewModel : ViewModel() {
    private val repository: YourRepository = YourRepository()

    fun getYourData(): LiveData<YourModel?> {
        return repository.getYourData()
    }
}