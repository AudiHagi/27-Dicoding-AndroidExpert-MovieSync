package com.dicoding.moviesync.ui.trending

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.moviesync.core.movie.data.MovieResource
import com.dicoding.moviesync.core.movie.ui.MovieAdapter
import com.dicoding.moviesync.di.trendModule
import com.dicoding.moviesync.tvshow.databinding.ActivityTrendingBinding
import com.dicoding.moviesync.ui.detailtrending.DetailTrendingActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


class TrendingActivity : AppCompatActivity() {

    private val trendViewModel: TrendingViewModel by viewModel()
    private lateinit var trendBind: ActivityTrendingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        trendBind = ActivityTrendingBinding.inflate(layoutInflater)
        setContentView(trendBind.root)

        setSupportActionBar(trendBind.toolbar)

        loadKoinModules(trendModule)

        supportActionBar?.title = "Movies Trending"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getMovieTrending()
    }

    private fun getMovieTrending() {
        val movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = { selectedData ->
            val detailIntent = Intent(this, DetailTrendingActivity::class.java)
            detailIntent.putExtra(DetailTrendingActivity.EXTRA_DATA, selectedData)
            startActivity(detailIntent)
        }
        trendViewModel.movie.observe(this) { movie ->
            if (movie != null) {
                when (movie) {
                    is MovieResource.Loading -> trendBind.pbLoading.visibility =
                        View.VISIBLE

                    is MovieResource.Success -> {
                        trendBind.pbLoading.visibility = View.GONE
                        movieAdapter.setData(movie.data)
                    }

                    is MovieResource.Error -> {
                        trendBind.pbLoading.visibility = View.GONE
                    }
                }
            }
        }
        with(trendBind.rvListMovie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}