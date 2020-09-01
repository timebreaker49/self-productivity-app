package com.example.selfproductivityapp.entry

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.selfproductivityapp.database.ActivitiesDatabaseDao

class EntryViewModelFactory (private val selectedDate: String, private val database: ActivitiesDatabaseDao): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EntryViewModel::class.java)){
            return EntryViewModel(selectedDate, database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}