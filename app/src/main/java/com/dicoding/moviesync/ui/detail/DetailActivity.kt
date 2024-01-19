package com.dicoding.moviesync.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.dicoding.moviesync.R
import com.dicoding.moviesync.core.movie.domain.model.Movie
import com.dicoding.moviesync.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.DecimalFormat

class DetailActivity : AppCompatActivity() {

    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var detailBind: ActivityDetailBinding
    private val link = "https://image.tmdb.org/t/p/w500"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBind = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBind.root)

        val detailMovie = intent.getParcelableExtra<Movie>(EXTRA_DATA)
        //detailBind.pbLoading.visibility = View.GONE
        showDetailMovie(detailMovie)

        detailBind.ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun showDetailMovie(detailMovie: Movie?) {
        detailMovie?.let {
            detailBind.tvMovieName.text = detailMovie.title
            detailBind.tvReleasedate.text = detailMovie.date
            detailBind.tvDescription.text = detailMovie.overview
            val ratingBarValue = (detailMovie.voteAvg / 2).toFloat()
            val decimalFormat = DecimalFormat(getString(R.string.pattern_rating))
            val formattedRating = decimalFormat.format(ratingBarValue)
            detailBind.tvRating.text = formattedRating
            Glide.with(this@DetailActivity)
                .load(link + detailMovie.poster)
                .into(detailBind.ivPoster)
            Glide.with(this@DetailActivity)
                .load(link + detailMovie.poster)
                .into(detailBind.ivPosterBackground)

            var statusFavorite = detailMovie.isFavorite
            setStatusFavorite(statusFavorite)
            detailBind.ivFavorite.setOnClickListener {
                statusFavorite = !statusFavorite
                val message =
                    if (statusFavorite) getString(
                        R.string.add_to_fav,
                        detailMovie.title
                    ) else getString(
                        R.string.remove_from_fav,
                        detailMovie.title
                    )
                detailViewModel.setFavoriteMovie(detailMovie, statusFavorite)
                Toast.makeText(this, "$message Favorite", Toast.LENGTH_SHORT).show()
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            detailBind.ivFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite
                )
            )
        } else {
            detailBind.ivFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_unfavorite
                )
            )
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

}