package com.jefferson.testeaiko

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.app_test.ui.navigation.DetailsScreenArgs
import com.example.app_test.ui.navigation.MainScreen
import com.example.app_test.ui.screen.DetailsScreen
import com.example.app_test.ui.screen.MainScreen
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import com.jefferson.testeaiko.ui.theme.TesteAikoTheme
import com.jefferson.testeaiko.viewmodel.ActualLocation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        LocationServices.getFusedLocationProviderClient(this).lastLocation.addOnCompleteListener {
            task: Task<Location> ->
            if (task.isSuccessful && task.result != null) {
                ActualLocation.latitude = task.result.latitude?: 0.0
                ActualLocation.longitude = task.result.longitude?: 0.0
        }
        enableEdgeToEdge()
        setContent {
            TesteAikoTheme {
                NavGraph()
            }
        }
    }
}

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = MainScreen,
        modifier = modifier
    ) {
        composable<MainScreen> { MainScreen(navController) }
        composable<DetailsScreenArgs> {
            DetailsScreen(
                navController,
                it.toRoute<DetailsScreenArgs>()
            )
        }

    }
}
}