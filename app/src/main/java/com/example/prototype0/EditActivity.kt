package com.example.prototype0

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.prototype0.databinding.ActivityEditBinding
import com.example.prototype0.databinding.ActivityMainBinding

class EditActivity : AppCompatActivity() {

    lateinit var binding: ActivityEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditBinding.inflate(layoutInflater) //room
        setContentView(binding.root)

        val externalMessage = intent.getStringExtra("key")
        binding.textTest.text = externalMessage

    }
}