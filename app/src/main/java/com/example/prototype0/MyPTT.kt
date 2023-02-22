package com.example.prototype0

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.nfc.NdefMessage
import android.os.Build
import android.provider.Settings.Global.getString
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.prototype0.MainActivity.Companion.CHANNEL_ID
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



    fun Notification(Ntitle: String, Nmessage: String){//context
        val NOTIFICATION_ID = 102
            // Create the NotificationChannel, but only on API 26+ because ПОЗЖЕ
            // the NotificationChannel class is new and not in the support library
            /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = "My Notification"//getString(R.string.channel_name)
                val descriptionText = "My Notification"//getString(R.string.channel_description)
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                    description = descriptionText
                }
                // Register the channel with the system
                val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }*/
        // Create an explicit intent for an Activity in your app
        //val intent = Intent(MainActivity, AlertDetails::class.java).apply {
        //    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        //}
        //val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)?
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("My notification")
            .setContentText("Hello World!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            // Set the intent that will fire when the user taps the notification
            //.setContentIntent(pendingIntent)
            .setAutoCancel(true)
        //...

        
        //val builder = NotificationCompat.Builder(applicationContext, MainActivity.CHANNEL_ID)
        //    .setSmallIcon(R.drawable.ic_launcher_background)
        //    .setContentTitle(Ntitle)
        //    .setContentText(Nmessage)
        //    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        //val notificationManager = NotificationManagerCompat.from(Context.NOTIFICATION_SERVICE)//applicationContext.getSystemService(Context.NOTIFICATION_SERVICE)
        //notificationManager.notify(NOTIFICATION_ID, builder.build())

    }



}
