package com.example.kotlinframework.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class FrameworkWork(appContext: Context, params: WorkerParameters): CoroutineWorker(appContext, params) {
    /*
    this class will be used in mainActivity to define work
    */
    companion object{
        const val WORK_NAME = "FrameworkWorker"
    }
    override suspend fun doWork(): Result {
        /*
        do schedule works like saving data in database and more
        all work should be done in try block
         */
        return try {
            // You can do whatever you want
            Result.success()
        }catch (e:Exception){
            Result.retry()
        }
    }

}