package com.example.testscrollingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.testscrollingapp.aboutscreen.AboutScreen
import com.example.testscrollingapp.home.HomeScreen
import com.example.testscrollingapp.routes.Routes
import com.example.testscrollingapp.ui.theme.TestScrollingAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestScrollingAppTheme {
                var startDestination by remember { mutableStateOf<Routes>(Routes.HomeScreen) }
                val navController = rememberNavController()
                val scope = rememberCoroutineScope()

                NavHost(
                    navController = navController,
                    startDestination = startDestination
                ) {
                    composable<Routes.HomeScreen> {
                        HomeScreen {
                        }
                    }
                    composable<Routes.AboutSection> {
                        AboutScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestScrollingAppTheme {
        Greeting("Android")
    }
}