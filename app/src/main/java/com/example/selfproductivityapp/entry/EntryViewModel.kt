package com.example.selfproductivityapp.entry

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.example.selfproductivityapp.convertEpochToTimeFormatted
import com.example.selfproductivityapp.database.ActivitiesDatabaseDao
import com.example.selfproductivityapp.database.ActivitiesDay
import com.example.selfproductivityapp.isValidTime
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

    private val _addOrUpdate = MutableLiveData<String>()
    val addOrUpdate: MutableLiveData<String>
        get() = _addOrUpdate

    private val _startTime = MutableLiveData<String>()
    val startTime: MutableLiveData<String>
        get() = _startTime

    private val _endTime = MutableLiveData<String>()
    val endTime: MutableLiveData<String>
        get() = _endTime

    private val _saveEntry = MutableLiveData<Boolean>()
    val saveEntry: LiveData<Boolean>
        get() = _saveEntry

    private val toastLiveData = MutableLiveData<ValueWrapper<String>>()
    val toast : LiveData<ValueWrapper<String>>
        get() = toastLiveData

    init {
        initializeEntry()
        _date.value = selectedDate
    }

    // Init function
    private fun initializeEntry() {
        if (chosenEntry == null) {
            newThing = brandNew
            mIsEdit = false
            _addOrUpdate.value = "Add new entry"
        } else {
            newThing = chosenEntry.copy()
            _startTime.value = convertEpochToTimeFormatted(newThing.startTimeMilli,"HH:mm")
            _endTime.value = convertEpochToTimeFormatted(newThing.endTimeMilli, "HH:mm")
            mIsEdit = true
            _addOrUpdate.value = "Update entry"
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

    fun validateEntry() : Boolean {
        if (!isValidTime(_startTime.value)) {
            toastLiveData.value = ValueWrapper("Valid start time is required")
            return false
        }
        if (!isValidTime(_endTime.value)) {
            toastLiveData.value = ValueWrapper("Valid end time is required")
            return false
        }
        if (newThing.category.isNullOrEmpty()) {
            toastLiveData.value = ValueWrapper("Category is required")
            return false
        }
        if (newThing.description.isNullOrEmpty()) {
            toastLiveData.value = ValueWrapper("Description is required")
            return false
        }
        return true
    }

    // Model operations
    fun onAddUpdateEntry() {
        _saveEntry.value = true
    }

    // helper methods
    private fun transformTimeToStore() {
        // converting string time entries to long epoch
        newThing.startTimeMilli = timeToEpochTime(_date.value.toString(), _startTime.value)
        newThing.endTimeMilli = timeToEpochTime(_date.value.toString(), _endTime.value)
    }
}

// creates a wrapper for the live data toast event which I only want to show once
open class ValueWrapper<T>(private val value: T) {
    private var isConsumed = false
    fun get(): T? =
        if (isConsumed) {
            null
        } else {
            isConsumed = true
            value
        }
}