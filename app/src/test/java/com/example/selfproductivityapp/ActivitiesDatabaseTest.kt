package com.example.selfproductivityapp

import androidx.room.Room
import com.example.selfproductivityapp.database.ActivitiesDatabase
import com.example.selfproductivityapp.database.ActivitiesDatabaseDao
import androidx.test.platform.app.InstrumentationRegistry
import com.example.selfproductivityapp.database.ActivitiesDay
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.IOException

@RunWith(RobolectricTestRunner::class)
class ActivitiesDatabaseTest {

    private lateinit var activitiesDao: ActivitiesDatabaseDao
    private lateinit var db: ActivitiesDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        db = Room.inMemoryDatabaseBuilder(context, ActivitiesDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        activitiesDao = db.activitiesDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetDay() {
        val day = ActivitiesDay()
        activitiesDao.insert(day)
        val today = activitiesDao.getAllEntries()
        assertEquals(today?.description, "")
    }








}