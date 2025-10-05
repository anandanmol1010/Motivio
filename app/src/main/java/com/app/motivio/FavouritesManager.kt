package com.app.motivio

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

class FavouritesManager(context: Context) {
    private val sharedPreferences: SharedPreferences = 
        context.getSharedPreferences("motivio_favourites", Context.MODE_PRIVATE)
    
    private val favouriteQuotes = mutableStateListOf<String>()
    
    init {
        loadFavourites()
    }
    
    fun getFavourites(): SnapshotStateList<String> = favouriteQuotes
    
    fun addToFavourites(quote: String) {
        if (!favouriteQuotes.contains(quote)) {
            favouriteQuotes.add(quote)
            saveFavourites()
        }
    }
    
    fun removeFromFavourites(quote: String) {
        favouriteQuotes.remove(quote)
        saveFavourites()
    }
    
    fun isFavourite(quote: String): Boolean {
        return favouriteQuotes.contains(quote)
    }
    
    private fun loadFavourites() {
        val savedQuotes = sharedPreferences.getStringSet("favourite_quotes", emptySet()) ?: emptySet()
        favouriteQuotes.clear()
        favouriteQuotes.addAll(savedQuotes)
    }
    
    private fun saveFavourites() {
        val editor = sharedPreferences.edit()
        editor.putStringSet("favourite_quotes", favouriteQuotes.toSet())
        editor.apply()
    }
}
