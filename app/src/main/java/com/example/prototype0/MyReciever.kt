package com.example.prototype0

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaParser
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi

class MyReciever: BroadcastReceiver() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onReceive(context: Context?, intent: Intent?) {
        val alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        val mp = MediaPlayer.create(context, alert)
        if(mp != null){
            mp.setVolume(100f, 100f)
            mp.start()
            mp.setOnCompletionListener{ mp -> mp.release()}
        }
        Log.e("tagg", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")
    }
}