package com.example.selfproductivityapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ActivitiesDatabaseDao {

    @Insert
    fun insert(activity: ActivitiesDay)

    @Update
    fun update(activity: ActivitiesDay)

    @Delete
    fun delete(activity: ActivitiesDay)

    @Query("SELECT * from daily_activities_table WHERE entryId = :key")
    fun get(key: Long): ActivitiesDay?

    @Query("SELECT * FROM daily_activities_table WHERE date = :date")
    fun get(date: String?): LiveData<List<ActivitiesDay>>

    @Query("SELECT * FROM daily_activities_table ORDER BY entryId DESC LIMIT 1000")
    fun getAllEntries(): LiveData<List<ActivitiesDay>>
}