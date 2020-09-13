package com.example.selfproductivityapp.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "daily_activities_table")
data class ActivitiesDay (
    @PrimaryKey(autoGenerate = true)
    var entryId: Long = 0L,

    @ColumnInfo(name = "start_time_milli")
    var startTimeMilli: Long = 0L,

    @ColumnInfo(name = "end_time_milli")
    var endTimeMilli: Long = 0L,

    @ColumnInfo(name = "description")
    var description: String = "",

    @ColumnInfo(name = "category")
    var category: String = ""
): Parcelable {
    fun copy() : ActivitiesDay {
        return ActivitiesDay(entryId, startTimeMilli, endTimeMilli, description, category)
    }
}