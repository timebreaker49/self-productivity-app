package com.example.selfproductivityapp.entry

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
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

    var newThing: ActivitiesDay

    private val brandNew: ActivitiesDay
        get() {
            return ActivitiesDay()
        }

    private val _date = MutableLiveData<String>()
    val date: MutableLiveData<String>
        get() = _date

    private val _startTime = MutableLiveData<String>()
    val startTime: MutableLiveData<String>
        get() = _startTime

    private val _endTime = MutableLiveData<String>()
    val endTime: MutableLiveData<String>
        get() = _endTime

    init {
        isDateSet()
        if (chosenEntry == null) {
            newThing = brandNew
        } else {
            newThing = chosenEntry.copy()
            _startTime.value = convertEpochToTimeFormatted(newThing.startTimeMilli)
            _endTime.value = convertEpochToTimeFormatted(newThing.endTimeMilli)
        }
    }

    private suspend fun insert(entry: ActivitiesDay) {
        withContext(Dispatchers.IO) {
            database.insert(entry)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun transformTimeToStore() {
        newThing.startTimeMilli = timeToEpochTime("September 1, 2020", _startTime.value)
        newThing.endTimeMilli = timeToEpochTime(_date.value.toString(), _endTime.value)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onAddNewEntry() {
        transformTimeToStore()
        viewModelScope.launch {
            insert(newThing)
        }
    }

    private fun isDateSet() {
        Log.i("SelectedDate", "Selected date: $selectedDate")
        if (selectedDate == 0.toString()) {
            Log.i("DateError", "no date set")
            _date.value = "September 1, 2020"
            // would ideally like to call function to extract start date from startTimeMilli
        } else {
            _date.value = selectedDate
        }
    }
}