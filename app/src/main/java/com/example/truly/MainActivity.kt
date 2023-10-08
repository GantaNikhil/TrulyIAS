package com.example.truly

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.truly.databinding.ActivityMainBinding
import com.example.truly.ui.theme.TrulyTheme

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