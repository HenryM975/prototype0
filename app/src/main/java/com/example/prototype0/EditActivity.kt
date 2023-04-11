package com.example.prototype0

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.prototype0.databinding.ActivityEditBinding
import com.example.prototype0.db.DB0

class EditActivity : AppCompatActivity() {

    lateinit var binding: ActivityEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditBinding.inflate(layoutInflater) //room
        setContentView(binding.root)

        val externalMessage = intent.getStringExtra("key")
        binding.textTest.text = externalMessage

        val db0 = DB0.getDB0(this)
        val id = db0.getDao().SelectId(96)
        Toast.makeText(this, "$id", Toast.LENGTH_SHORT).show()
        binding.textTest1.text = id.toString()


    }
}