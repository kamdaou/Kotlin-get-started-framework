package com.example.kotlinframework.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

// attributes can be added to sweet our need
@Parcelize
@Entity(tableName = "FrameworkData")
data class FrameworkData (
    @PrimaryKey
    val id: Long = 0L,

    @ColumnInfo(name="column_name")
    val column: String
):Parcelable