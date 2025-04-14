package com.si23b.aplikasi_database_film2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.si23b.aplikasi_database_film2.api.adaptor.TopAnimeAdapter
import com.si23b.aplikasi_database_film2.databinding.FragmentTopAnimeBinding
import androidx.fragment.app.activityViewModels
import com.si23b.aplikasi_database_film2.api.model.AnimeData


class TopAnimeFragment : Fragment() {

    private lateinit var binding: FragmentTopAnimeBinding
    private val viewModel: AnimeViewModel by activityViewModels()
    private lateinit var topAnimeAdapter: TopAnimeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopAnimeBinding.inflate(inflater, container, false)
        binding.tvHeader.text = "Top Anime"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        setupObservers()
        loadInitialData()
    }

    private fun setupAdapter() {
        topAnimeAdapter = TopAnimeAdapter()

        binding.rvTopAnime.apply {
            adapter = topAnimeAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)

            // Untuk animasi perubahan data
            itemAnimator = null
        }
    }

    private fun setupObservers() {
        viewModel.topAnimeList.observe(viewLifecycleOwner) { animeList ->
            animeList?.let {
                topAnimeAdapter.submitList(it)
                handleEmptyState(it)
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { error ->
            error?.takeIf { it.isNotEmpty() }?.let {
                showError(it)
            }
        }
    }

    private fun loadInitialData() {
        if (viewModel.topAnimeList.value.isNullOrEmpty()) {
            viewModel.loadTopAnime()
        }
    }

    private fun handleEmptyState(animeList: List<AnimeData>) {
        binding.apply {
            if (animeList.isEmpty()) {
                rvTopAnime.visibility = View.GONE
                tvEmptyView.visibility = View.VISIBLE
            } else {
                rvTopAnime.visibility = View.VISIBLE
                tvEmptyView.visibility = View.GONE
            }
        }
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        binding.tvEmptyView.visibility = View.VISIBLE
        binding.tvEmptyView.text = message
    }
}
