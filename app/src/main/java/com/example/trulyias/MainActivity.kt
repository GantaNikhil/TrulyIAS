package com.example.trulyias

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.MediaController
import android.widget.VideoView
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import com.example.trulyias.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val videoPath = "android.resource://$packageName/${R.raw.video_song}"

        val uri = Uri.parse(videoPath)
        binding.videoView.setVideoURI(uri)

        val mediaController = MediaController(this)

        binding.videoView.setMediaController(mediaController)
        mediaController.setAnchorView(binding.videoView)

        binding.showHandout.setOnClickListener {
            val intent = Intent(this, PdfViewerActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}