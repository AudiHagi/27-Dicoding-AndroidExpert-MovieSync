package com.dicoding.moviesync.ui.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.dicoding.moviesync.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            delay(splashTime)
            withContext(Dispatchers.Main) {
                val intentToMain =
                    Intent(this@SplashScreenActivity, MainActivity::class.java)
                startActivity(intentToMain)
                finish()
            }
        }
    }

    companion object {
        const val splashTime: Long = 1500
    }

}