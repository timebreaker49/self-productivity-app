package com.example.selfproductivityapp

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.history_fragment.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * A simple [Fragment] subclass as the history destination in the navigation.
 */
class HistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.history_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //やらなきゃいけない：See if this is used in the calendar fragment -- if not, delete!
        // 注意事項
/*        val date = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        val text = date.format(formatter)

        month.text = "Your $text"*/

        view.findViewById<Button>(R.id.button_history).setOnClickListener {
            findNavController().navigate(R.id.action_HistoryFragment_to_HomeFragment)
        }
    }
}