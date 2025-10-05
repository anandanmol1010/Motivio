package com.app.motivio

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onNavigateToFavourites: () -> Unit,
    onAddToFavourites: (String) -> Unit,
    favouritesManager: FavouritesManager
) {
    val motivationalQuotes = listOf(
        "The only way to do great work is to love what you do. - Steve Jobs",
        "Life is what happens to you while you're busy making other plans. - John Lennon",
        "The future belongs to those who believe in the beauty of their dreams. - Eleanor Roosevelt",
        "It is during our darkest moments that we must focus to see the light. - Aristotle",
        "The way to get started is to quit talking and begin doing. - Walt Disney",
        "Don't let yesterday take up too much of today. - Will Rogers",
        "You learn more from failure than from success. Don't let it stop you. Failure builds character. - Unknown",
        "If you are working on something that you really care about, you don't have to be pushed. The vision pulls you. - Steve Jobs"
    )
    
    var currentQuote by remember { mutableStateOf(motivationalQuotes.random()) }
    var showSnackbar by remember { mutableStateOf(false) }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // App Title
        Text(
            text = "Motivio",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        
        // Quote Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Text(
                text = currentQuote,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                lineHeight = 24.sp
            )
        }
        
        // Buttons
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = {
                    currentQuote = motivationalQuotes.random()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Next Quote")
            }
            
            Button(
                onClick = {
                    onAddToFavourites(currentQuote)
                    showSnackbar = true
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !favouritesManager.isFavourite(currentQuote)
            ) {
                Text(
                    if (favouritesManager.isFavourite(currentQuote)) 
                        "Already in Favourites" 
                    else 
                        "Mark as Favourite"
                )
            }
            
            OutlinedButton(
                onClick = onNavigateToFavourites,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("View Favourites")
            }
        }
    }
    
    // Snackbar for feedback
    if (showSnackbar) {
        LaunchedEffect(showSnackbar) {
            kotlinx.coroutines.delay(2000)
            showSnackbar = false
        }
        
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Card(
                modifier = Modifier.padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.inverseSurface
                )
            ) {
                Text(
                    text = "Quote added to favourites!",
                    color = MaterialTheme.colorScheme.inverseOnSurface,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}
