package com.example.prototype0

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Thread.sleep

class MyWM(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams){
    override fun doWork(): Result {
        Log.e("tagg", "MyWorker0 is start")
        sleep(5000)
        Log.e("tagg", "MyWorker0 is complete")
        return Result.success()
    }
}
class MyWM1(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams){
    override fun doWork(): Result {
        Log.e("tagg", "MyWorker1 is start")
        var a = inputData.getInt("begin", 0)
        val b = inputData.getInt("end",     1000)
        var s:Long = 0
        while(a<b) s += a++
        val out = Data.Builder().putLong("res", s).build()
        Log.e("tagg", "MyWorker1 is complete\n result is ${out.getLong("res", 0)}")
        return Result.success(out)
    }
}

