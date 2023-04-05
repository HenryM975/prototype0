package com.example.prototype0

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.prototype0.databinding.ActivityMainBinding
import com.example.prototype0.databinding.ActivityNewMessageBinding

class NewMessageActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewMessageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_message)

        binding = ActivityNewMessageBinding.inflate(layoutInflater) //room
        setContentView(binding.root)

        binding.backToMainButton.setOnClickListener {
            val backToMain = Intent(this, MainActivity::class.java)
            startActivity(backToMain)
        }

        //save
        val db0 = DB0.getDB0(this)
        binding.NewMessageSaveButton.setOnClickListener  {addData(db0)}
    }
    fun addData(dB0: DB0){
        Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show()//не выводио только тут
        val item = DB0Entity(null, binding.editColumn0DB.text.toString(),  binding.editColumn1DB.text.toString())
        Thread{
            dB0.getDao().AddItem(item)
        }.start()
        val backToMain = Intent(this, MainActivity::class.java)
        startActivity(backToMain)
    }
}