package com.example.kotlinframework.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// This database can contain more than one entities, so add some data classes then add the here.
@Database(entities = [FrameworkData::class], version = 1, exportSchema = false)
abstract class FrameworkDatabase: RoomDatabase() {
    abstract val frameworkDatabaseDao: FrameworkDatabaseDao
    companion object{
        @Volatile
        private var INSTANCE: FrameworkDatabase?=null
        fun getInstance(context: Context): FrameworkDatabase {
            /*
            returns database instance
            */
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(context,
                        FrameworkDatabase::class.java,
                        "asteroid_database")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }

        }
    }
}