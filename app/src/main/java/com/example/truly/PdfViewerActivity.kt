package com.example.truly

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.truly.databinding.ActivityPdfviewerBinding

class PdfViewerActivity : ComponentActivity() {
    private lateinit var binding: ActivityPdfviewerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPdfviewerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val inputStream = resources.openRawResource(R.raw.doe);

        try {
            binding.pdfView.fromStream(inputStream).load()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}