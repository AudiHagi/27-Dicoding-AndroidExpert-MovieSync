package com.dicoding.moviesync.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.moviesync.core.movie.ui.MovieAdapter
import com.dicoding.moviesync.databinding.FragmentFavoriteBinding
import com.dicoding.moviesync.ui.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private var _binding: FragmentFavoriteBinding? = null
    private val favoriteBind
        get() = _binding
            ?: throw IllegalStateException("_binding should not be null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return favoriteBind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = { selectedData ->
                val detailIntent = Intent(activity, DetailActivity::class.java)
                detailIntent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                startActivity(detailIntent)
            }

            favoriteViewModel.favoriteMovie.observe(viewLifecycleOwner) { dataMovie ->
                if (dataMovie.isNotEmpty()) {
                    favoriteBind.layoutEmpty.visibility = View.GONE
                    movieAdapter.setData(dataMovie)
                } else {
                    favoriteBind.layoutEmpty.visibility = View.VISIBLE
                    movieAdapter.setData(dataMovie)
                }
                favoriteBind.pbLoading.visibility = View.GONE
            }

            with(favoriteBind.rvListMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}