package com.si23b.aplikasi_database_film2.api.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load

import com.si23b.aplikasi_database_film2.R
import com.si23b.aplikasi_database_film2.api.model.AnimeData


class AnimeAdapter : RecyclerView.Adapter<AnimeAdapter.GridViewHolder>() {

    private var animeList = emptyList<AnimeData>()

    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPoster: ImageView = itemView.findViewById(R.id.imgPoster)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_anime, parent, false)
        return GridViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        val anime = animeList[position]

        holder.tvTitle.text = anime.title

        // Load gambar dengan Coil
        holder.imgPoster.load(anime.images.jpg.imageUrl) {
            crossfade(true)
        }
    }

    override fun getItemCount() = animeList.size

    fun submitList(list: List<AnimeData>) {
        animeList = list
        notifyDataSetChanged()
    }


}
