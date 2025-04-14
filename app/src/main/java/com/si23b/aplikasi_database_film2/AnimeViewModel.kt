package com.si23b.aplikasi_database_film2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.si23b.aplikasi_database_film2.api.model.AnimeData
import com.si23b.aplikasi_database_film2.api.service.AnimeRepository


import kotlinx.coroutines.launch

class AnimeViewModel : ViewModel() {
    private val repository = AnimeRepository()
    val animeList = MutableLiveData<List<AnimeData>>()
    val errorMessage = MutableLiveData<String>()

    // LiveData untuk Top Anime
    val topAnimeList = MutableLiveData<List<AnimeData>>()

    val currentTitle = MutableLiveData<String>("New This Season")

    fun searchAnime(query: String) {
        viewModelScope.launch {
            try {
                val response = repository.getAnimeSearch(query)
                animeList.postValue(response.data)
            } catch (e: Exception) {
                errorMessage.postValue("Error: ${e.message}")
            }
        }
    }

    // Untuk anime musim ini
    fun loadCurrentSeason() {
        viewModelScope.launch {
            try {
                val response = repository.getCurrentSeasonAnime()
                Log.d("API Response", response.toString()) // ✅ Cek data di Logcat
                animeList.postValue(response.data)
            } catch (e: Exception) {
                errorMessage.postValue("Season error: ${e.message}")
            }
        }
    }

    // Untuk top anime
    fun loadTopAnime() {
        viewModelScope.launch {
            try {
                val response = repository.getTopAnime()
                Log.d("API Response", response.toString()) // ✅ Cek data di Logcat
                topAnimeList.postValue(response.data)
            } catch (e: Exception) {
                errorMessage.postValue("TopAnime error: ${e.message}")
            }
        }
    }

}