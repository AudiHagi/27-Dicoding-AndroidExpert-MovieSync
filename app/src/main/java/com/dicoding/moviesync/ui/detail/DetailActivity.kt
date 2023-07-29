package com.dicoding.moviesync.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.moviesync.R

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }
}