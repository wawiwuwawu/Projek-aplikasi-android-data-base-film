package com.si23b.aplikasi_database_film2.api.model

import com.google.gson.annotations.SerializedName



data class JikanResponse(
    @SerializedName("data") val data: List<AnimeData>,
    @SerializedName("pagination") val pagination: Pagination
)

data class AnimeData(
    @SerializedName("mal_id") val malId: Int,
    @SerializedName("title") val title: String,
    @SerializedName("images") val images: ImageData,
    @SerializedName("score") val score: Double,
    @SerializedName("synopsis") val synopsis: String
)

data class ImageData(
    @SerializedName("jpg") val jpg: ImageUrls
)

data class ImageUrls(
    @SerializedName("image_url") val imageUrl: String
)

data class Pagination(
    @SerializedName("last_visible_page") val lastVisiblePage: Int,
    @SerializedName("has_next_page") val hasNextPage: Boolean
)
