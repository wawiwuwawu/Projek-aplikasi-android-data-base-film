package com.si23b.aplikasi_database_film2

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.si23b.aplikasi_database_film2.api.adapter.AnimeAdapter
import com.si23b.aplikasi_database_film2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: AnimeViewModel
    private lateinit var adapter: AnimeAdapter
    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Setup RecyclerView
        adapter = AnimeAdapter()
        binding.recyclerView.adapter = adapter

        // Setup GridLayoutManager (2 kolom)
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)

        // Setup ViewModel
        viewModel = ViewModelProvider(this)[AnimeViewModel::class.java]
        viewModel.animeList.observe(this) { list ->
            adapter.submitList(list)
        }
        viewModel.errorMessage.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }


        setupSearch()
        setupBottomNavigation()

        // Load data awal
        viewModel.loadCurrentSeason()


    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener  { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    viewModel.currentTitle.value = "New This Season" // Update judul ke Home
                    showHomeScreen()
                    true
                }
                R.id.nav_TopAnime -> {
                    viewModel.currentTitle.value = "Top Anime" // Update judul ke Top Anime
                    showTopAnimeFragment()
                    true
                }
                else -> false
            }
        }
    }

    private fun showHomeScreen() {
        // Tampilkan RecyclerView dan sembunyikan fragment container
        binding.recyclerView.visibility = View.VISIBLE
        binding.fragmentContainer.visibility = View.GONE
        viewModel.loadCurrentSeason()
    }

    private fun showTopAnimeFragment() {
        // Sembunyikan RecyclerView dan tampilkan fragment
        binding.recyclerView.visibility = View.GONE
        binding.fragmentContainer.visibility = View.VISIBLE

        // Load fragment Top Anime
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, TopAnimeFragment())
            .commit()
        viewModel.loadTopAnime()
    }



    private fun setupRecyclerView() {
        adapter = AnimeAdapter()
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = this@MainActivity.adapter
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this)[AnimeViewModel::class.java]
        viewModel.animeList.observe(this) { list ->
            adapter.submitList(list)
        }
        viewModel.errorMessage.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupSearch() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrEmpty()) {
                    // Jika query kosong, load kembali data musim ini
                    viewModel.loadCurrentSeason()
                } else {
                    viewModel.searchAnime(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Optional: Implementasi real-time search
                if (newText.isNullOrEmpty()) {
                    viewModel.loadCurrentSeason()
                }
                return true
            }
        })
    }
}


