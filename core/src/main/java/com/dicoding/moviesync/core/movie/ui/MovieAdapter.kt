package com.dicoding.moviesync.core.movie.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.moviesync.core.R
import com.dicoding.moviesync.core.databinding.MovieLayoutBinding
import com.dicoding.moviesync.core.movie.domain.model.Movie

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ListViewHolder>() {

    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null
    val link = "https://image.tmdb.org/t/p/w500"

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return

        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = listData.size
            override fun getNewListSize(): Int = newListData.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val oldItem = listData[oldItemPosition]
                val newItem = newListData[newItemPosition]
                return oldItem.movieId == newItem.movieId
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val oldItem = listData[oldItemPosition]
                val newItem = newListData[newItemPosition]
                return oldItem == newItem
            }
        })

        listData.clear()
        listData.addAll(newListData)
        diffResult.dispatchUpdatesTo(this)
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