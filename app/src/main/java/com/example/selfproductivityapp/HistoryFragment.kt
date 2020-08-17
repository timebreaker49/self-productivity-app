package com.example.selfproductivityapp

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.history_fragment.*
import java.text.DateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * A simple [Fragment] subclass as the history destination in the navigation.
 */
class HistoryFragment : Fragment() {

    lateinit var calendarView: CalendarView
    lateinit var dateView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.history_fragment, container, false)

        val calendar = Calendar.getInstance()
        dateView = root.findViewById(R.id.dateView)
        calendarView = root.findViewById(R.id.calendarView)
        calendarView.setOnDateChangeListener { _, year, month, day ->
            calendar.set(year,month,day)
            val dateFormatter = DateFormat.getDateInstance(DateFormat.LONG)
            val formattedDate = dateFormatter.format(calendar.time)
            dateView.text = formattedDate
        }

        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_history).setOnClickListener {
            findNavController().navigate(R.id.action_HistoryFragment_to_HomeFragment)
        }
    }
}