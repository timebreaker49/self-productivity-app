package com.example.selfproductivityapp.day

import android.os.Build
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import com.example.selfproductivityapp.convertDurationToFormatted
import com.example.selfproductivityapp.convertEpochToTimeFormatted
import com.example.selfproductivityapp.database.ActivitiesDay

// TODO: Inverse data binding adapter

@BindingAdapter("getStartTimeFormatted")
@RequiresApi(Build.VERSION_CODES.O)
fun TextView.getStartTimeFormatted(item: ActivitiesDay) {
    val pattern = "HH:mm"
    text = convertEpochToTimeFormatted(item.startTimeMilli, pattern)
}

@BindingAdapter("getEndTimeFormatted")
@RequiresApi(Build.VERSION_CODES.O)
fun TextView.getEndTimeFormatted(item: ActivitiesDay) {
    val pattern = "HH:mm"
    text = convertEpochToTimeFormatted(item.endTimeMilli, pattern)
}

@BindingAdapter("getDurationFormatted")
@RequiresApi(Build.VERSION_CODES.O)
fun TextView.getDurationFormatted(item: ActivitiesDay) {
    text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, context.resources)
}