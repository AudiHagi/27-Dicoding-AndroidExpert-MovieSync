package com.dicoding.moviesync.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.moviesync.core.data.Resource
import com.dicoding.moviesync.core.ui.MovieAdapter
import com.dicoding.moviesync.databinding.FragmentMovieBinding
import com.dicoding.moviesync.ui.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {

    private val movieViewModel: MovieViewModel by viewModel()
    private var _binding: FragmentMovieBinding? = null
    private val movieBind get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return movieBind.root
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
            movieViewModel.movie.observe(viewLifecycleOwner) { movie ->
                if (movie != null) {
                    when (movie) {
                        is Resource.Loading -> movieBind.pbLoading.visibility = View.VISIBLE
                        is Resource.Success -> {
                            movieBind.pbLoading.visibility = View.GONE
                            movieAdapter.setData(movie.data)
                        }

                        is Resource.Error -> {
                            movieBind.pbLoading.visibility = View.GONE
                        }
                    }
                }
            }
            with(movieBind.rvListmovie) {
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