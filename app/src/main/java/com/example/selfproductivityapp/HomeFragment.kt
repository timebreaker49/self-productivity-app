package com.example.selfproductivityapp

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.history_fragment.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //display the current month
        val date = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        val text = date.format(formatter)

        month.text = "Your $text"

        view.findViewById<Button>(R.id.random_button).setOnClickListener {
            //locate the action from navigation
            findNavController().navigate(R.id.action_HomeFragment_to_HistoryFragment)
            //
            val showCountTextView = view.findViewById<TextView>(R.id.textview_first)
            val currentCount = showCountTextView.text.toString().toInt()
            val action = HomeFragmentDirections.actionHomeFragmentToHistoryFragment(currentCount)
            findNavController().navigate(action)
        }

        view.findViewById<Button>(R.id.count_button).setOnClickListener {
            countMe(view)
        }

        view.findViewById<Button>(R.id.toast_button).setOnClickListener {
            val myToast = Toast.makeText(context, "Hello Toast", Toast.LENGTH_SHORT)
            myToast.show()
        }
    }

    private fun countMe(view: View) {

        //Get the text view
        val showCountTextView = view.findViewById<TextView>(R.id.textview_first)

        // Get the value of the text view
        val countString = showCountTextView.text.toString()

        //Convert value of the string to an int
        var count = countString.toInt()
        count++

        //Display the new value in the text view
        showCountTextView.text = count.toString()
    }
}