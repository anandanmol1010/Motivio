package com.app.motivio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.motivio.ui.theme.MotivioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MotivioTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MotivationalQuotesApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MotivationalQuotesApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val context = LocalContext.current
    val favouritesManager = remember { FavouritesManager(context) }
    
    NavHost(
        navController = navController,
        startDestination = "main",
        modifier = modifier
    ) {
        composable("main") {
            MainScreen(
                onNavigateToFavourites = { navController.navigate("favourites") },
                onAddToFavourites = { quote ->
                    favouritesManager.addToFavourites(quote)
                },
                favouritesManager = favouritesManager
            )
        }
        composable("favourites") {
            FavouritesScreen(
                favouriteQuotes = favouritesManager.getFavourites(),
                onNavigateBack = { navController.popBackStack() },
                onRemoveFromFavourites = { quote ->
                    favouritesManager.removeFromFavourites(quote)
                }
            )
        }
    }
}