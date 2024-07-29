package com.example.app_test.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.app_test.ui.navigation.DetailsScreenArgs
import com.example.app_test.viewmodel.DetailScreenViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.jefferson.testeaiko.R
import com.jefferson.testeaiko.model.Location


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavHostController, detailsArgs: DetailsScreenArgs) {
    val viewModel: DetailScreenViewModel = viewModel()
    val detailsState = viewModel.detailScreenData.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getDetailsScreenData(detailsArgs.id)
    }

    Column {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White,
                titleContentColor = Color.Black,
            ),
            title = { Text("Detalhes") },
            navigationIcon = {
                Icon(
                    modifier = Modifier.clickable { navController.popBackStack() },
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Voltar"
                )
            },
        )

        MapScreen()

        LazyColumn(
            modifier = Modifier
                .padding(8.dp)
                .background(color = Color.White)
        ) {
            items(detailsState.value.size) { item ->
                DetailItem(detailsState.value[item])
            }
        }
    }

}


@Composable
fun DetailItem(detailItem: Location) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.frenteonibus),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .padding(end = 16.dp)
            )
            Text(
                text = detailItem.cp.toString(),
                modifier = Modifier
                    .border(width = 2.dp, color = Color.Blue, shape = RoundedCornerShape(4.dp))
                    .padding(8.dp),
                fontSize = 16.sp
            )
        }
        Text(
            text = detailItem.np,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = detailItem.ed,
            fontSize = 16.sp
        )
        HorizontalDivider(color = Color.Gray, thickness = 0.5.dp)
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapScreen() {
    val locationPermissionState =
        rememberPermissionState(android.Manifest.permission.ACCESS_FINE_LOCATION)

    if (locationPermissionState.status.shouldShowRationale) {
        // Show rationale and request permission
        Text("Location permission is required to show your location on the map.")
    }

    if (locationPermissionState.status.isGranted) {
        val singapore = LatLng(1.3521, 103.8198)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(singapore, 15f)
        }

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(isMyLocationEnabled = true)
        ) {
            Marker(
                state = MarkerState(position = singapore),
                title = "One Marker"
            )
            // Add more markers here
        }
    } else {
        LaunchedEffect(Unit) {
            locationPermissionState.launchPermissionRequest()
        }
    }
}