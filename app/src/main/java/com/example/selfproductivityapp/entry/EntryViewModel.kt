package com.example.selfproductivityapp.entry

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.example.selfproductivityapp.database.ActivitiesDatabaseDao
import com.example.selfproductivityapp.database.ActivitiesDay
import kotlinx.coroutines.*
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class EntryViewModel(private val selectedDate: String, val database: ActivitiesDatabaseDao
                     ): ViewModel() {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _date = MutableLiveData<String>()
    val date: LiveData<String>
        get() = _date

    private val _startTime = MutableLiveData<String>()
    val startTime: MutableLiveData<String>
        get() = _startTime

    val newThing: ActivitiesDay

    init {
        _date.value = selectedDate
        _startTime.value = ""
        newThing = brandNew
    }

    val brandNew: ActivitiesDay
        get() {
            return ActivitiesDay()
        }

    private suspend fun insert(entry: ActivitiesDay) {
        withContext(Dispatchers.IO) {
            database.insert(entry)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onAddNewEntry() {

        val pattern = "MMMM d, yyyy HH:mm"
        val completeStart = date.value + " " + startTime.value
        Log.i("CompleteStart!!", "Here is the new entry: ${completeStart}!")

        val formatter = DateTimeFormatter.ofPattern(pattern)
        Log.i("Formatter!!", "Here is the new entry: ${formatter}!")

        val localDateTime = LocalDateTime.parse(completeStart, formatter)
        Log.i("LocalDateTime!!", "Here is the new entry: ${localDateTime}!")

        val finalTime = DateTimeFormatter.ofPattern(pattern).format(localDateTime)
        Log.i("FinalDateTime!!", "||||||||||||    Final date time        ||||||||||||||||: ${finalTime}!")

        Log.i("newEntry!!", "Here is the new entry: ${date.value}!")
        viewModelScope.launch {
            insert(newThing)

        }
    }
}