package com.example.prototype0

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.*
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.timer


class MainActivity : AppCompatActivity(), TimePickerDialog.OnTimeSetListener{

    companion object {
        const val NOTIFICATION_ID = 101
        const val CHANNEL_ID = "channelID"
    }
    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, MainActivity::class.java)
        intent.apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

       // while (true) {
            //Thread.sleep(5000)
            var testButton0 = findViewById<Button>(R.id.test0)
            testButton0.setOnClickListener{
            val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Напоминание")
                .setContentText("Пора покормить кота")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)

            val notificationManager = NotificationManagerCompat.from(this)
            notificationManager.notify(NOTIFICATION_ID, builder.build())

            // или
            //with(NotificationManagerCompat.from(this)) {
            //    notify(NOTIFICATION_ID, builder.build()) // посылаем уведомление
            //}
        }
    }
    fun  setting(v: View){
            val tpf = MyTimePicker()
            tpf.show(supportFragmentManager, "picker")
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onTimeSet(p0: TimePicker?, hourOfDay: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)
        val am = getSystemService(ALARM_SERVICE) as AlarmManager
        val myIntent = Intent(applicationContext, MyReciever::class.java)
        val mypIntent = PendingIntent.getBroadcast(applicationContext, 10, myIntent, 0)
        am.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, mypIntent)
    }

    fun start_wm(v: View){
        val t1 = OneTimeWorkRequestBuilder<MyWM>().build()
        val d = Data.Builder().putInt("begin", Int.MIN_VALUE).putInt("end", Int.MAX_VALUE).build()
        val t2 = OneTimeWorkRequestBuilder<MyWM1>().setInputData(d).build()
        WorkManager.getInstance(this)
            .beginWith(t1)
            //.then(t3Test)
            //.then(Arrays.asList(t1, t2))
            //.then(t2)
            .enqueue()
    }

    fun PTT(v: View){

    }


}
