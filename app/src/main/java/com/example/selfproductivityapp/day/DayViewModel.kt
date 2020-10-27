package com.example.selfproductivityapp.day

import android.app.Application
import androidx.lifecycle.*
import com.example.selfproductivityapp.database.ActivitiesDatabaseDao
import com.example.selfproductivityapp.database.ActivitiesDay
import kotlinx.coroutines.*

class DayViewModel(selectedDate: String,
                   dataSource:ActivitiesDatabaseDao,
                   application: Application): AndroidViewModel(application) {

    val database = dataSource

    private val _date = MutableLiveData<String>()
    val date: LiveData<String>
        get() = _date

    private val _addEntry = MutableLiveData<Boolean>()
    val addEntry: LiveData<Boolean>
        get() = _addEntry

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var today: LiveData<List<ActivitiesDay>> = getSpecifiedDate(selectedDate)

    init {
        _date.value = selectedDate
    }

    private fun getSpecifiedDate(selectedDate: String) : LiveData<List<ActivitiesDay>> {
        return database.get(selectedDate)
    }

    class DayViewModelFactory (private val selectedDate: String,
    private val dataSource: ActivitiesDatabaseDao, private val application: Application) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(DayViewModel::class.java)){
                return DayViewModel(selectedDate, dataSource, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    fun addEntryClicked() {
        _addEntry.value = true
    }
}