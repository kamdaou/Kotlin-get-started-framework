package com.example.kotlinframework.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FrameworkDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: FrameworkData)

    @Update
    suspend fun update(data: FrameworkData)

    @Query("SELECT * FROM frameworkdata WHERE id=:id")
    suspend fun getData(id:Long): FrameworkData

    @Query("SELECT * FROM frameworkdata")
    fun getAllData():LiveData<List<FrameworkData>?>

    @Query("DELETE FROM frameworkdata")
    suspend fun deleteAll():Int

    @Query("DELETE FROM FrameworkData WHERE id=:id")
    suspend fun delete(id: Long):Int

}