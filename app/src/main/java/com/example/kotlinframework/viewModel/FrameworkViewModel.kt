package com.example.kotlinframework.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlinframework.database.FrameworkData
import com.example.kotlinframework.database.FrameworkDatabaseDao
import kotlinx.coroutines.launch

class FrameworkViewModel(var database: FrameworkDatabaseDao, application: Application): AndroidViewModel(application) {
    lateinit var frameworkDataList:LiveData<List<FrameworkData>?>

    init {
        viewModelScope.launch{
            frameworkDataList = getAllData()
        }
    }
    fun getAllData(): LiveData<List<FrameworkData>?> {
        return database.getAllData()
    }

    suspend fun insertData(data: FrameworkData){
        viewModelScope.launch {
            database.insert(data)
        }
    }

    suspend fun deleteData(data: FrameworkData){
        viewModelScope.launch {
            database.delete(data.id)
        }
    }

    suspend fun deleteAllData(){
        viewModelScope.launch {
            database.deleteAll()
        }
    }

    suspend fun getData(data: FrameworkData){
        viewModelScope.launch {
            database.getData(data.id)
        }
    }

    suspend fun updateData(data: FrameworkData){
        viewModelScope.launch {
            database.update(data)
        }
    }


}