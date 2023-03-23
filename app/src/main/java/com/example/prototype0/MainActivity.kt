package com.example.prototype0

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
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
import androidx.appcompat.widget.AppCompatButton
//import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.RecyclerView
//import androidx.lifecycle.LiveData
import androidx.work.*
import com.example.prototype0.adapter.DataAdapter
import com.example.prototype0.databinding.ActivityMainBinding
import com.example.prototype0.model.DataModel
import java.util.*
import java.util.concurrent.TimeUnit
//import kotlin.concurrent.timer?


class MainActivity : AppCompatActivity(), TimePickerDialog.OnTimeSetListener{

    //var id = 0



    var time = 5000
    companion object {
        const val NOTIFICATION_ID = 101
        const val CHANNEL_ID = "channelID"
    }
    //val db0 = DB0.getDB0(this)


    lateinit var binding: ActivityMainBinding//room
    lateinit var adapter: DataAdapter//RecyclerView
    lateinit var recyclerView: RecyclerView

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater) //room
        setContentView(binding.root)

        //add to db
        val db0 = DB0.getDB0(this)
        binding.buttonDB.setOnClickListener {//room
            Toast.makeText(this, "buttonDB", Toast.LENGTH_SHORT).show()//не выводио только тут
            val item = DB0Entity(null, binding.editColumn0DB.text.toString(),  binding.editColumn1DB.text.toString())
            Thread{
                db0.getDao().AddItem(item)
            }.start()
        }

        //get from db
        db0.getDao().GetAll().asLiveData().observe(this){ list->  //list == it
            binding.textDB.text = ""
            list.forEach {
                val text = "Id: ${it.id} Column0: ${it.Column0} Column1: ${it.Column1}\n"
                binding.textDB.append(text)
            }
            }

        //clear db
        binding.buttonClearDB.setOnClickListener {
            db0.getDao().GetAll().asLiveData().observe(this) { list ->
                list.forEach {
                    Thread{
                        db0.getDao().Delete(it)
                    }.start()
                    Toast.makeText(this, "Delete+", Toast.LENGTH_SHORT).show()//не выводило только тут
                }
            }
        }

        //size db
        binding.buttonSizeDB.setOnClickListener{
            db0.getDao().Size().asLiveData().observe(this){
                Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
            }
        }




        //add new button test
        binding.buttonNewBtn.setOnClickListener {

        }










        //setContentView(R.layout.activity_main)
        val intent = Intent(this, MainActivity::class.java)
        intent.apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

       // while (true) {
            //Thread.sleep(5000)
            var testButton0 = findViewById<Button>(R.id.test0)
            testButton0.setOnClickListener{
                Toast.makeText(this, "buttonDB", Toast.LENGTH_SHORT).show()
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





        //add components
        binding.addMessageButton.setOnClickListener {
            Toast.makeText(this, "+", Toast.LENGTH_SHORT).show()
            val toNewMessage = Intent(this, NewMessageActivity::class.java)
            startActivity(toNewMessage)
        }





        //RecyclerView
        initial(db0)






    }

    private fun initial(db0: DB0) { //RecyclerView
        recyclerView = binding.recyclerViewData
        adapter = DataAdapter(this)
        recyclerView.adapter = adapter
        adapter.setList(myData(db0))
    }
    fun myData(db0: DB0): ArrayList<DataModel>{ //RecyclerView
        val dataList = ArrayList<DataModel>()

        db0.getDao().GetAll().asLiveData().observe(this){ list->  //list == it
            binding.textDB.text = ""
            list.forEach {
                dataList.add(DataModel(it.id.toString(), it.Column0.toString(), it.Column1.toString()))
            }
        }
        return dataList
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
            .beginWith(t2)
            //.then(t3Test)
            //.then(Arrays.asList(t1, t2))
            .then(t1)
            .enqueue()
    }



    fun PTT(v: View){
        val time = Data.Builder().putInt("time", time).build()
        val t1 = OneTimeWorkRequestBuilder<MyPTT>().setInputData(time).build()//+
        val t2 = PeriodicWorkRequestBuilder<MyPTT>(16, TimeUnit.MINUTES).setInputData(time).build()//сиюминутно и через 16 минут
        WorkManager.getInstance(this)
            //.beginWith(t1)//+
            //.then(Toast.makeText(applicationContext, "Пора покормить кота!", Toast.LENGTH_SHORT).show())
            .enqueue(t2)
    }


}

