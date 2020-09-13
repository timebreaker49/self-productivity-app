package com.example.selfproductivityapp.day

import android.os.Build
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseMethod
import com.example.selfproductivityapp.convertDurationToFormatted
import com.example.selfproductivityapp.convertEpochToTimeFormatted
import com.example.selfproductivityapp.database.ActivitiesDay
import com.example.selfproductivityapp.timeToEpochTime

// TODO: Inverse data binding adapter

@BindingAdapter("getStartTimeFormatted")
@RequiresApi(Build.VERSION_CODES.O)
fun TextView.getStartTimeFormatted(item: ActivitiesDay) {
    text = convertEpochToTimeFormatted(item.startTimeMilli)
}

@BindingAdapter("getEndTimeFormatted")
@RequiresApi(Build.VERSION_CODES.O)
fun TextView.getEndTimeFormatted(item: ActivitiesDay) {
    text = convertEpochToTimeFormatted(item.endTimeMilli)
}

@BindingAdapter("getDurationFormatted")
@RequiresApi(Build.VERSION_CODES.O)
fun TextView.getDurationFormatted(item: ActivitiesDay) {
    text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, context.resources)
}