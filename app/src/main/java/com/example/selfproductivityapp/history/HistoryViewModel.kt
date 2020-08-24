package com.example.selfproductivityapp.history

import android.util.Log
import android.widget.CalendarView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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

    }

/*  --> Haven't figured out how to make the onClick binding work for functions that require
        multiple parameters
    fun onSelectedDate(view: CalendarView, year: Int, month: Int, day:Int) {
        val calendar = Calendar.getInstance()
            calendar.set(year,month,day)
        val dateFormatter = DateFormat.getDateInstance(DateFormat.LONG)
        val formattedDate = dateFormatter.format(calendar.time)
        _date.value = formattedDate
        // when a user selects a date from the calendar
    }*/
}