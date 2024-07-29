package com.example.app_test.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.app_test.model.BussInfo
import com.example.app_test.ui.navigation.DetailsScreenArgs
import com.example.app_test.viewmodel.MainScreenViewModel
import com.jefferson.testeaiko.R

@Composable
fun MainScreen(navController: NavHostController) {
    val viewModel: MainScreenViewModel = viewModel()
    val searchText = remember {
        mutableStateOf("")
    }
    LaunchedEffect(Unit) {
        viewModel.auth()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
    ) {
        Spacer(modifier = Modifier.height(25.dp))
        SearchBar(searchText = searchText)
        Spacer(modifier = Modifier.height(10.dp))
        BussColumn(viewModel, navController)
    }

    if(searchText.value.length > 4) {
        viewModel.getSearch(searchText.value)
    }


}

@Composable
private fun SearchBar(searchText: MutableState<String>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = searchText.value,
            onValueChange = { searchText.value = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Procure por uma linha") }
        )
    }
}

@Composable
private fun BussColumn(viewModel: MainScreenViewModel, navController: NavHostController) {
    val listState = viewModel.mainScreenData.collectAsState()
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(8.dp)
    ) { items(listState.value.size) { item ->
            GridItemCard(item = listState.value[item], navController)
        }
    }
}

@Composable
fun GridItemCard(item: BussInfo, navController: NavHostController) {
    Card(
        modifier = Modifier
            .clickable {
                navController.navigate(DetailsScreenArgs(item.codigoIdentificadorLinha.toString()))
            }
            .padding(8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(id = R.drawable.busstop),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(50.dp)

            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = item.codigoIdentificadorLinha.toString())
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = item.terminalPrimario.toString())
        }
    }
}

