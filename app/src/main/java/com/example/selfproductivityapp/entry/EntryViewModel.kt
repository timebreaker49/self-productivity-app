package com.example.selfproductivityapp.entry

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.selfproductivityapp.database.ActivitiesDatabaseDao
import com.example.selfproductivityapp.day.DayViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class EntryViewModel(private val selectedDate: String, val database: ActivitiesDatabaseDao
                     ): ViewModel() {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _date = MutableLiveData<String>()
    val date: LiveData<String>
        get() = _date

    init {
        _date.value = selectedDate
    }
}