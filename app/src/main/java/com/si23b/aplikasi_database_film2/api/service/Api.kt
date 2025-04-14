package com.si23b.aplikasi_database_film2.api.service


import com.si23b.aplikasi_database_film2.api.model.JikanResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface JikanApiService {
    @GET("anime")
    suspend fun getAnimeList(
        @Query("q") query: String,
        @Query("page") page: Int = 1
    ): JikanResponse

    @GET("seasons/now")
    suspend fun getCurrentSeasonAnime(
        @Query("page") page: Int = 1
    ): JikanResponse

    @GET("top/anime")
    suspend fun getTopAnime(
        @Query("page") page: Int = 1
    ): JikanResponse
}

object RetrofitInstance {
    private const val BASE_URL = "https://api.jikan.moe/v4/"

    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    val api: JikanApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(JikanApiService::class.java)
    }
}

class AnimeRepository {
    suspend fun getAnimeSearch(query: String, page: Int = 1): JikanResponse {
        return try {
            RetrofitInstance.api.getAnimeList(query, page)
        } catch (e: Exception) {
            throw e
        }
    }

    // Untuk anime musim ini
    suspend fun getCurrentSeasonAnime(page: Int = 1): JikanResponse {
        return try {
            RetrofitInstance.api.getCurrentSeasonAnime(page)
        } catch (e: Exception) {
            throw e
        }
    }

    // Untuk Top anime
    suspend fun getTopAnime(page: Int = 1): JikanResponse {
        return try {
            RetrofitInstance.api.getTopAnime(page)
        } catch (e: Exception) {
            throw e
        }
    }


}