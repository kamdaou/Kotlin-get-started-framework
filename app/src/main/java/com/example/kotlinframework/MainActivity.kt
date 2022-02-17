package com.example.kotlinframework

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.*
import com.example.kotlinframework.work.FrameworkWork
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // calling function to init work
        delayedInit()
        setContentView(R.layout.activity_main)
    }

    private fun delayedInit(){
        /*
        launch the worker
        */
        applicationScope.launch {
            setupRecurrentWork()
        }
    }

    private fun setupRecurrentWork(){
        /*
        * Use worker to create a recurrent action
        */
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresBatteryNotLow(true)
            .setRequiresCharging(true)
            .apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setRequiresDeviceIdle(true)
                }
            }.build()

        // work will repeat everyday
        val repeatingRequest = PeriodicWorkRequestBuilder<FrameworkWork>(1, TimeUnit.DAYS)
            .setConstraints(constraints)
            .build()
        WorkManager.getInstance().enqueueUniquePeriodicWork(
            FrameworkWork.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest)
    }
}