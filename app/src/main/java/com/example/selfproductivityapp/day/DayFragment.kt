package com.example.selfproductivityapp.day

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.selfproductivityapp.R
import com.example.selfproductivityapp.databinding.DayFragmentBinding


class DayFragment : Fragment() {

    private lateinit var viewModel: DayViewModel
    private lateinit var viewModelFactory: DayViewModel.DayViewModelFactory
    private lateinit var binding: DayFragmentBinding
    private lateinit var date: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: DayFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.day_fragment,
            container, false
        )

        viewModelFactory = DayViewModel.DayViewModelFactory(DayFragmentArgs.fromBundle(requireArguments()).selectedDate)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DayViewModel::class.java)

        viewModel.addEntry.observe(viewLifecycleOwner, Observer<Boolean> { isClicked ->
            if (isClicked) addNewEntry()
        })

        binding.dayViewModel = viewModel
        date = binding.date

        return binding.root
    }

    private fun addNewEntry() {
        val selectedDate = date.text.toString()
        val action = DayFragmentDirections.actionDayFragmentToEntryFragment(selectedDate)
        NavHostFragment.findNavController(this).navigate(action)
    }

}