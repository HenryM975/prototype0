package com.example.prototype0

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Thread.sleep

class MyPTT {
    class MyWM(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams){
        override fun doWork(): Result {
            sleep(5000)

            return Result.success()
        }

    }
}