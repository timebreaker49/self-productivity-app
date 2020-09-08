package com.example.selfproductivityapp.day

import android.os.Build
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import com.example.selfproductivityapp.convertDurationToFormatted
import com.example.selfproductivityapp.convertEpochToTimeFormatted
import com.example.selfproductivityapp.database.ActivitiesDay

@BindingAdapter("startTimeFormatted")
@RequiresApi(Build.VERSION_CODES.O)
fun TextView.setStartTimeFormatted(item: ActivitiesDay) {
    text = convertEpochToTimeFormatted(item.startTimeMilli)
}

@BindingAdapter("endTimeFormatted")
@RequiresApi(Build.VERSION_CODES.O)
fun TextView.setEndTimeFormatted(item: ActivitiesDay) {
    text = convertEpochToTimeFormatted(item.endTimeMilli)
}

@BindingAdapter("durationFormatted")
@RequiresApi(Build.VERSION_CODES.O)
fun TextView.setDurationFormatted(item: ActivitiesDay) {
    text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, context.resources)
}