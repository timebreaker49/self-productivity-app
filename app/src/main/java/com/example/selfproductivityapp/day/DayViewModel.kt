package com.example.selfproductivityapp.day

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.selfproductivityapp.history.HistoryViewModel

class DayViewModel(selectedDate: String): ViewModel() {

    private val _date = MutableLiveData<String>()
    val date: LiveData<String>
        get() = _date

    private val _addEntry = MutableLiveData<Boolean>()
    val addEntry: LiveData<Boolean>
        get() = _addEntry

    init {
        _date.value = selectedDate
    }

    class DayViewModelFactory (private val selectedDate: String) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(DayViewModel::class.java)){
                return DayViewModel(selectedDate) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    fun addEntryClicked() {
        _addEntry.value = true
    }
}