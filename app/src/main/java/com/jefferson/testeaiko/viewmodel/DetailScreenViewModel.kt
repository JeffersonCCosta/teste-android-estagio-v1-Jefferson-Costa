package com.example.app_test.viewmodel

import androidx.lifecycle.ViewModel
import com.example.app_test.model.DetailItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailScreenViewModel: ViewModel()  {

    private val _detailScreenData = MutableStateFlow<List<DetailItem>>(listOf())
    val detailScreenData = _detailScreenData.asStateFlow()


    fun getDetailsScreenData(id: String) {
        _detailScreenData.value = listOf(
            DetailItem("Vai la"),
            DetailItem("Vai nao"),
            DetailItem("Vai sim"),
            DetailItem("Vai nada"),
            DetailItem("Vai xd"),
        )
    }
}