package com.example.prototype0

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.prototype0.MainActivity.Companion.CHANNEL_ID
import java.lang.Thread.sleep


class MyPTT(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        Log.e("tagg", "MyPTT is start")
        val time = inputData.getInt("time",0)
            //sleep(5000)
        //Toast.makeText(applicationContext, "Пора покормить кота!", Toast.LENGTH_SHORT).show()
        Notification(time.toString(), "Nmessage")
        Log.e("tagg", "MyPTT is complete")
        return Result.success()
    }



    fun Notification(Ntitle: String, Nmessage: String){//context
        val NOTIFICATION_ID = 102


            

        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = Ntitle
            val descriptionText = Nmessage
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            notificationManager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
            .setSmallIcon(R.drawable.q2)
            .setContentTitle(Ntitle)
            .setContentText(Nmessage)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            // Set the intent that will fire when the user taps the notification
            //.setContentIntent(pendingIntent)
            .setAutoCancel(true)
        notificationManager.notify(NOTIFICATION_ID,builder.build());





    }




}
