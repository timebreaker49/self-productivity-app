package com.example.selfproductivityapp.entry

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.selfproductivityapp.database.ActivitiesDatabaseDao
import com.example.selfproductivityapp.database.ActivitiesDay

class EntryViewModelFactory (private val selectedDate: String,
                             private val database: ActivitiesDatabaseDao,
                             private val chosenEntry: ActivitiesDay?): ViewModelProvider.Factory{
    @RequiresApi(Build.VERSION_CODES.O)
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EntryViewModel::class.java)){
            return EntryViewModel(selectedDate, database, chosenEntry) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}