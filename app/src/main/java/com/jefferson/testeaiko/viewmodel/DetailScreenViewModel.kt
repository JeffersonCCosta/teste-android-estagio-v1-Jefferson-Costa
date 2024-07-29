package com.example.app_test.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jefferson.testeaiko.model.Location
import com.jefferson.testeaiko.repository.BussRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map

class DetailScreenViewModel: ViewModel()  {

    private val repository: BussRepository = BussRepository()
    private val _detailScreenData = MutableStateFlow<List<Location>>(listOf())
    val detailScreenData = _detailScreenData.asStateFlow()


    fun getDetailsScreenData(id: String) {
        repository.getParadas(id).map {
                data -> _detailScreenData.value = data!!
        }.launchIn(viewModelScope)
    }
}