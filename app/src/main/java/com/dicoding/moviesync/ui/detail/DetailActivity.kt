package com.dicoding.moviesync.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.dicoding.moviesync.R
import com.dicoding.moviesync.core.domain.model.Movie
import com.dicoding.moviesync.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var detailBind: ActivityDetailBinding
    val link = "https://www.themoviedb.org/t/p/w220_and_h330_face"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBind = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBind.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val detailMovie = intent.getParcelableExtra<Movie>(EXTRA_DATA)
        detailBind.pbLoading.visibility = View.GONE
        showDetailMovie(detailMovie)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun showDetailMovie(detailMovie: Movie?) {
        detailMovie?.let {
            supportActionBar?.title = "Movie"
            detailBind.tvMoviename.text = detailMovie.title
            detailBind.tvReleasedate.text = detailMovie.date
            detailBind.tvDescription.text = detailMovie.overview
            val ratingBarValue = (detailMovie.voteAvg / 2).toFloat()
            detailBind.tvRating.text = ratingBarValue.toString()
            detailBind.rbVote.rating = ratingBarValue
            Glide.with(this@DetailActivity)
                .load(link + detailMovie.poster)
                .into(detailBind.ivPoster)

            var statusFavorite = detailMovie.isFavorite
            setStatusFavorite(statusFavorite)
            detailBind.fbFavorite.setOnClickListener {
                statusFavorite = !statusFavorite
                val message =
                    if (statusFavorite) "Add ${detailMovie.title} To" else "Remove ${detailMovie.title} From"
                detailViewModel.setFavoriteMovie(detailMovie, statusFavorite)
                Toast.makeText(this, "$message Favorite", Toast.LENGTH_SHORT).show()
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            detailBind.fbFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite
                )
            )
        } else {
            detailBind.fbFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_unfavorite
                )
            )
        }
    }

}