package com.example.prototype0

import android.content.Context
import android.nfc.NdefMessage
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Thread.sleep


class MyPTT(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        Log.e("tagg", "MyPTT is start")
        sleep(5000)
        //Toast.makeText(applicationContext, "Пора покормить кота!", Toast.LENGTH_SHORT).show()
        Notification("Ntitle", "Nmessage")
        Log.e("tagg", "MyPTT is complete")



        return Result.success()
    }



    fun Notification(Ntitle: String, Nmessage: String){
        val notificationId = 2
        val builder = NotificationCompat.Builder(applicationContext, MainActivity.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(Ntitle)
            .setContentText(Nmessage)
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE)
        //notificationManager.notify(notificationId, builder.build())?
    }

}
