package com.si23b.aplikasi_database_film2.api.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.si23b.aplikasi_database_film2.R
import com.si23b.aplikasi_database_film2.api.model.AnimeData


class TopAnimeAdapter : RecyclerView.Adapter<TopAnimeAdapter.TopAnimeViewHolder>() {

    private var topAnimeList = emptyList<AnimeData>()

    inner class TopAnimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPoster: ImageView = itemView.findViewById(R.id.iv_poster)
        val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        val ratingBar: RatingBar = itemView.findViewById(R.id.rating_bar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopAnimeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_top_anime, parent, false)
        return TopAnimeViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopAnimeViewHolder, position: Int) {
        val anime = topAnimeList[position]

        // Set data ke view
        holder.tvTitle.text = anime.title
        holder.ratingBar.rating = anime.score?.toFloat() ?: 0f

        // Load gambar dengan Coil
        anime.images.jpg.imageUrl?.let {
            holder.imgPoster.load(it) {
                crossfade(true)
//                placeholder(R.drawable.placeholder_image)
            }
        }
    }

    override fun getItemCount() = topAnimeList.size

    fun submitList(list: List<AnimeData>) {
        topAnimeList = list
        notifyDataSetChanged()
    }
}
