package com.example.kotlinframework

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinframework.database.FrameworkDatabaseDao
import com.example.kotlinframework.viewModel.FrameworkViewModel

class FrameworkViewModelFactory(val databaseDao: FrameworkDatabaseDao, private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FrameworkViewModel::class.java))
            return FrameworkViewModel(databaseDao, application) as T
        throw IllegalArgumentException("unknown view model class")
    }

}