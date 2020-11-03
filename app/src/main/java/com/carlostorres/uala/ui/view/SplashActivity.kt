package com.carlostorres.uala.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.carlostorres.uala.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intentSplash = Intent(this, SearchActivity::class.java)
        startActivity(intentSplash)
        finish()
    }
}