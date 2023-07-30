package com.dicoding.moviesync.ui.detailtrending

import android.os.Bundle
import android.view.View
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
    val link = "https://www.themoviedb.org/t/p/w220_and_h330_face"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailTrendBind = ActivityDetailTrendingBinding.inflate(layoutInflater)
        setContentView(detailTrendBind.root)

        supportActionBar?.title = "Movie Details"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val detailMovie = intent.getParcelableExtra<Movie>(DetailActivity.EXTRA_DATA)
        detailTrendBind.pbLoading.visibility = View.GONE
        showDetailMovie(detailMovie)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun showDetailMovie(detailMovie: Movie?) {
        detailMovie?.let {
            supportActionBar?.title = "Movie"
            detailTrendBind.tvMoviename.text = detailMovie.title
            detailTrendBind.tvReleasedate.text = detailMovie.date
            detailTrendBind.tvDescription.text = detailMovie.overview
            val ratingBarValue = (detailMovie.voteAvg / 2).toFloat()
            val decimalFormat = DecimalFormat("#.#")
            val formattedRating = decimalFormat.format(ratingBarValue)
            detailTrendBind.tvRating.text = formattedRating
            detailTrendBind.rbVote.rating = ratingBarValue
            Glide.with(this@DetailTrendingActivity)
                .load(link + detailMovie.poster)
                .into(detailTrendBind.ivPoster)
        }
    }

}