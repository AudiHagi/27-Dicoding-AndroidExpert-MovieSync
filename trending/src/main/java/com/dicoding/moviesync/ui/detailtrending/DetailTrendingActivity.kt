package com.dicoding.moviesync.ui.detailtrending

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dicoding.moviesync.core.movie.domain.model.Movie
import com.dicoding.moviesync.tvshow.databinding.ActivityDetailTrendingBinding
import com.dicoding.moviesync.ui.detail.DetailActivity
import java.text.DecimalFormat

class DetailTrendingActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var detailTrendBind: ActivityDetailTrendingBinding
    private val link = "https://image.tmdb.org/t/p/w500"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailTrendBind = ActivityDetailTrendingBinding.inflate(layoutInflater)
        setContentView(detailTrendBind.root)

        val detailMovie = intent.getParcelableExtra<Movie>(DetailActivity.EXTRA_DATA)
//        detailTrendBind.pbLoading.visibility = View.GONE
        showDetailMovie(detailMovie)

        detailTrendBind.ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun showDetailMovie(detailMovie: Movie?) {
        detailMovie?.let {
            detailTrendBind.tvMoviename.text = detailMovie.title
            detailTrendBind.tvReleasedate.text = detailMovie.date
            detailTrendBind.tvDescription.text = detailMovie.overview
            val ratingBarValue = (detailMovie.voteAvg / 2).toFloat()
            val decimalFormat = DecimalFormat("#.#")
            val formattedRating = decimalFormat.format(ratingBarValue)
            detailTrendBind.tvRating.text = formattedRating
            Glide.with(this@DetailTrendingActivity)
                .load(link + detailMovie.poster)
                .into(detailTrendBind.ivPoster)
            Glide.with(this@DetailTrendingActivity)
                .load(link + detailMovie.poster)
                .into(detailTrendBind.ivPosterBackground)
        }
    }

}