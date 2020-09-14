package com.example.selfproductivityapp.entry

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.example.selfproductivityapp.convertEpochToTimeFormatted
import com.example.selfproductivityapp.database.ActivitiesDatabaseDao
import com.example.selfproductivityapp.database.ActivitiesDay
import com.example.selfproductivityapp.timeToEpochTime
import kotlinx.coroutines.*

@RequiresApi(Build.VERSION_CODES.O)
class EntryViewModel(
    private val selectedDate: String?,
    val database: ActivitiesDatabaseDao,
    private val chosenEntry: ActivitiesDay?): ViewModel() {

    lateinit var newThing: ActivitiesDay

    private var mIsEdit: Boolean = false

    private val brandNew: ActivitiesDay
        get() { return ActivitiesDay() }

    private val _date = MutableLiveData<String>()
    val date: MutableLiveData<String>
        get() = _date

    private val _startTime = MutableLiveData<String>()
    val startTime: MutableLiveData<String>
        get() = _startTime

    private val _endTime = MutableLiveData<String>()
    val endTime: MutableLiveData<String>
        get() = _endTime

    private val _saveEntry = MutableLiveData<Boolean>()
    val saveEntry: LiveData<Boolean>
        get() = _saveEntry

    init {
        initializeEntry()
        setDate()
    }

    // Init function
    private fun initializeEntry() {
        if (chosenEntry == null) {
            newThing = brandNew
            mIsEdit = false
        } else {
            newThing = chosenEntry.copy()
            _startTime.value = convertEpochToTimeFormatted(newThing.startTimeMilli,"HH:mm")
            _endTime.value = convertEpochToTimeFormatted(newThing.endTimeMilli, "HH:mm")
            mIsEdit = true
        }
    }

    // Database Operations! データベースのオペ //
    private suspend fun insert(entry: ActivitiesDay) {
        withContext(Dispatchers.IO) {
            database.insert(entry)
        }
    }

    private suspend fun update(entry: ActivitiesDay) {
        withContext(Dispatchers.IO) {
            database.update(entry)
        }
    }

    fun saveEntry() {
        transformTimeToStore()
        if (!mIsEdit) {
            viewModelScope.launch {
                insert(newThing)
            }
        } else {
            viewModelScope.launch {
                update(newThing)
            }
        }
    }

    // Model operations
    fun onAddUpdateEntry() {
        _saveEntry.value = true
    }

    // helper methods
    private fun transformTimeToStore() {
        // converting string time entries to long epoch
        newThing.startTimeMilli = timeToEpochTime("September 1, 2020", _startTime.value)
        newThing.endTimeMilli = timeToEpochTime(_date.value.toString(), _endTime.value)
    }

    private fun setDate() {
        if (selectedDate == 0.toString()) {
            _date.value = convertEpochToTimeFormatted(newThing.startTimeMilli, "MMMM d, yyyy")
        } else {
            _date.value = selectedDate
        }
    }
}