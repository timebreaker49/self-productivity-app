package com.example.selfproductivityapp.entry

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.selfproductivityapp.day.DayViewModel

class EntryViewModel(selectedDate: String): ViewModel() {

    private val _date = MutableLiveData<String>()
    val date: LiveData<String>
        get() = _date

    init {
        _date.value = selectedDate
    }


    class EntryViewModelFactory (private val selectedDate: String) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(EntryViewModel::class.java)){
                return EntryViewModel(selectedDate) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}