package com.app.motivio

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun QuoteCard(
    quote: String,
    modifier: Modifier = Modifier,
    onRemove: (() -> Unit)? = null
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        if (onRemove != null) {
            // Card with remove button for favourites screen
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = quote,
                    fontSize = 16.sp,
                    lineHeight = 22.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.weight(1f)
                )
                
                IconButton(
                    onClick = onRemove,
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Remove from favourites",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
//        else {
//            // Simple card for main screen
//            Text(
//                text = quote,
//                fontSize = 16.sp,
//                lineHeight = 22.sp,
//                textAlign = TextAlign.Start,
//                modifier = Modifier
//                    .padding(16.dp)
//                    .fillMaxWidth()
//            )
//        }
    }
}
