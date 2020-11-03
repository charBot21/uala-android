package com.carlostorres.uala.ui.view

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.carlostorres.uala.R
import com.carlostorres.uala.databinding.ActivityItemDetailBinding
import com.carlostorres.uala.model.interfaces.DetailListener
import com.carlostorres.uala.ui.viewmodel.DetailViewModel

class ItemDetailActivity : AppCompatActivity(), DetailListener {

    // Data binding and view model
    private lateinit var binding: ActivityItemDetailBinding
    private lateinit var viewModel: DetailViewModel

    // Data Received
    private var link: String ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_item_detail)
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        binding.detailvm = viewModel
        viewModel.listener = this

        getDataReceived()
    }


    fun getDataReceived() {
        link = intent.extras?.getString("linkVideo")

        val videoView = findViewById<VideoView>(R.id.videoView)
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        val uri = Uri.parse(link.toString())
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(uri)
        videoView.requestFocus()
        videoView.start()
    }

    override fun close() {
        finish()
    }
}