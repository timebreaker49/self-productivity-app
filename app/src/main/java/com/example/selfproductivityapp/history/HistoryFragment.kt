package com.example.selfproductivityapp.history

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.selfproductivityapp.R
import com.example.selfproductivityapp.databinding.HistoryFragmentBinding
import kotlinx.android.synthetic.main.home_fragment.*
import java.text.DateFormat
import java.util.*

/**
 * A simple [Fragment] subclass as the history destination in the navigation.
 */
class HistoryFragment : Fragment() {

    private lateinit var binding: HistoryFragmentBinding
    private lateinit var viewModel: HistoryViewModel
    private lateinit var calendarView : CalendarView
    private lateinit var date : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding: HistoryFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.history_fragment, container, false)

        // initializing our view model
        viewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)

        calendarView = binding.calendarView
        date = binding.dateView

        calendarView.setOnDateChangeListener { view, year, month, day ->
            onSelectedDate(view, year, month, day)
            sendSelectedDate()
        }

        binding.historyViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
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

    private fun sendSelectedDate() {
        val selectedDate = date.text.toString()
        val action = HistoryFragmentDirections.actionHistoryFragmentToDayFragment(selectedDate)
        NavHostFragment.findNavController(this).navigate(action)
    }

    private fun onSelectedDate(view: CalendarView, year: Int, month: Int, day:Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year,month,day)
        val dateFormatter = DateFormat.getDateInstance(DateFormat.LONG)
        val formattedDate = dateFormatter.format(calendar.time)
        date.text = formattedDate
        // when a user selects a date from the calendar
    }
}