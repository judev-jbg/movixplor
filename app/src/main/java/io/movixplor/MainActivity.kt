package io.movixplor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier

import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import io.movixplor.ui.navigation.NavGraph
import io.movixplor.ui.theme.MovixplorTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovixplorTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    Surface(modifier = Modifier.fillMaxSize()) {
                        NavGraph(navController = navController)
                    }
                }
            }
        }
    }
}
