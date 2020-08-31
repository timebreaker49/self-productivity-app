package com.example.selfproductivityapp.database

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

    @Query("SELECT * FROM daily_activities_table ORDER BY entryId DESC LIMIT 1")
    fun getDay(): ActivitiesDay?
}