package com.example.selfproductivityapp

import android.annotation.SuppressLint
import java.time.*
import android.content.res.Resources
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.format.DateTimeFormatter


@SuppressLint("SimpleDateFormat")
@RequiresApi(Build.VERSION_CODES.O)
fun convertEpochToTimeFormatted(timeMilli: Long, pattern: String): String {
    val epochToDateTime = LocalDateTime.ofEpochSecond(timeMilli, 0, ZoneOffset.UTC)
    val simpleTimeFormat = DateTimeFormatter.ofPattern(pattern)
    return simpleTimeFormat.format(epochToDateTime)
}

@SuppressLint("SimpleDateFormat")
@RequiresApi(Build.VERSION_CODES.O)
fun timeToEpochTime(date: String, enteredTime: String?): Long {
    val completeStart = "$date $enteredTime"
    val formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy HH:mm")
    return LocalDateTime.parse(completeStart, formatter).toEpochSecond(ZoneOffset.UTC)
}

@RequiresApi(Build.VERSION_CODES.O)
fun convertDurationToFormatted(startTimeMilli: Long, endTimeMilli: Long, res: Resources): String {
    val durationMilli = (endTimeMilli - startTimeMilli) * 1000
    val minute = (durationMilli / (1000 * 60)) % 60
    val hour = (durationMilli / (1000 * 60 * 60)) % 24
    return when {
        hour > 0 -> {
            res.getString(R.string.hours_and_minutes, hour, minute)
        }
        else -> {
            res.getString(R.string.minutes, minute)
        }
    }
}
