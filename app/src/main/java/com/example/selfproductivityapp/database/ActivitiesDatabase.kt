package com.example.selfproductivityapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ActivitiesDay::class], version = 1, exportSchema = false)
abstract class ActivitiesDatabase: RoomDatabase() {

    abstract val activitiesDatabaseDao: ActivitiesDatabaseDao

    companion object {

        private var INSTANCE: ActivitiesDatabase? = null

        fun getInstance(context: Context): ActivitiesDatabase{
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ActivitiesDatabase::class.java,
                        "daily_activities_table"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }
}