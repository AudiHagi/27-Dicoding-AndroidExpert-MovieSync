package com.dicoding.moviesync.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.moviesync.R
import com.dicoding.moviesync.core.domain.model.Movie
import com.dicoding.moviesync.databinding.MovieLayoutBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ListViewHolder>() {

    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null
    val link = "https://www.themoviedb.org/t/p/w220_and_h330_face"

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_layout, parent, false)
        )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = MovieLayoutBinding.bind(itemView)
        fun bind(data: Movie) {
            with(binding) {
                tvTitle.text = data.title
//                tvDate.text = data.date
//                val ratingBarValue = (data.voteAvg / 2).toFloat()
//                rbVote.rating = ratingBarValue
                Glide.with(itemView.context)
                    .load(link + data.poster)
                    .into(ivPoster)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

}