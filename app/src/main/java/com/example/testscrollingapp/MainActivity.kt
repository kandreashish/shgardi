package com.example.testscrollingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.testscrollingapp.aboutscreen.AboutScreenRoot
import com.example.testscrollingapp.aboutscreen.AboutSectionViewmodel
import com.example.testscrollingapp.celeb.CelebListScreenRoot
import com.example.testscrollingapp.celeb.CelebListScreenViewModel
import com.example.testscrollingapp.routes.Routes
import com.example.testscrollingapp.ui.theme.TestScrollingAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestScrollingAppTheme {
                val startDestination by remember { mutableStateOf<Routes>(Routes.HomeScreen) }
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = startDestination
                ) {
                    composable<Routes.HomeScreen> {
                        val homeScreenViewModel: CelebListScreenViewModel by viewModels()
                        LaunchedEffect(Unit) {
                            homeScreenViewModel.onCelebAction(null)
                        }
                        CelebListScreenRoot(homeScreenViewModel) { person ->
                            navController.navigate(Routes.AboutSection(person.id))
                        }
                    }
                    composable<Routes.AboutSection> {
                        val aboutSectionViewmodel: AboutSectionViewmodel by viewModels()
                        val args: Routes.AboutSection =  it.toRoute<Routes.AboutSection>()
                        AboutScreenRoot(args,aboutSectionViewmodel) {
                            navController.popBackStack()
                        }
                    }
                }
            }
        }
    }
}