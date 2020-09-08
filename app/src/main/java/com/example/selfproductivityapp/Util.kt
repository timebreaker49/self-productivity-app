package com.example.selfproductivityapp

import android.annotation.SuppressLint
import java.time.*
import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.selfproductivityapp.database.ActivitiesDay
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*


@SuppressLint("SimpleDateFormat")
@RequiresApi(Build.VERSION_CODES.O)
fun convertEpochToTimeFormatted(timeMilli: Long): String {
    val epochToDateTime = LocalDateTime.ofEpochSecond(timeMilli, 0, ZoneOffset.UTC)
    val simpleTimeFormat = DateTimeFormatter.ofPattern("HH:mm")
    return simpleTimeFormat.format(epochToDateTime)
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatActivity(entries: List<ActivitiesDay>, resources: Resources): Spanned {
    val sb = StringBuilder()
    sb.apply {
        entries.forEach {
            append("<br>")
            append("<b>ID:</b>")
            append("\t${it.entryId}<br>")
            append("<b>Start time:</b>")
            append("\t${it.startTimeMilli}<br>")
            append("<b>End time:</b>")
            append("\t${it.endTimeMilli}<br>")
            append("<b>Category:</b>")
            append("\t${it.category}<br>")
            append("<b>Description:</b>")
            append("\t${it.description}<br>")
        }
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}