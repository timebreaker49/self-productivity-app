package com.example.selfproductivityapp.history

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.DateFormat
import java.util.*

class HistoryViewModel() : ViewModel() {

    private val _date = MutableLiveData<String>()
    val date: LiveData<String>
        get() = _date

    init {
        Log.i("HistoryViewModel", "HistoryViewModel activated")
        _date.value = ""
        getCurrentDate()
    }

    private fun getCurrentDate() {
        val calendar = Calendar.getInstance()
        val dateFormatter = DateFormat.getDateInstance(DateFormat.LONG)
        val formattedDate = dateFormatter.format(calendar.time)
        _date.value = formattedDate
/*        calendarView.setOnDateChangeListener { _, year, month, day ->
            calendar.set(year,month,day)
        }*/
    }
}