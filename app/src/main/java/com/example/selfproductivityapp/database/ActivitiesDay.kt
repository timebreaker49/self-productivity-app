package com.example.selfproductivityapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_activities_table")
data class ActivitiesDay (
    @PrimaryKey(autoGenerate = true)
    var entryId: Long = 0L,

    @ColumnInfo(name = "start_time_milli")
    val startTimeMilli: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "end_time_milli")
    var endTimeMill: Long = startTimeMilli,

    @ColumnInfo(name = "description")
    var description: String = "",

    @ColumnInfo(name = "category")
    var category: String = ""
)