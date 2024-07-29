package com.example.app_test.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_test.model.BussInfo
import com.jefferson.testeaiko.repository.BussRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map

class MainScreenViewModel() : ViewModel() {

    private val repository: BussRepository = BussRepository()
    private val _mainScreenData = MutableStateFlow<List<BussInfo>>(listOf())
    val mainScreenData = _mainScreenData.asStateFlow()


    fun getSearch(text: String) {
        repository.getYourData(text).map {
                data -> _mainScreenData.value = data!!
        }.launchIn(viewModelScope)
    }

    fun auth() {
        repository.auth().map {
        }.launchIn(viewModelScope)
    }

}