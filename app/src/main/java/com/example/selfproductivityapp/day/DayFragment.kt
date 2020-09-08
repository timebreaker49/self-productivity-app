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
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.selfproductivityapp.R
import com.example.selfproductivityapp.database.ActivitiesDatabase
import com.example.selfproductivityapp.databinding.DayFragmentBinding


class DayFragment : Fragment() {

    private lateinit var viewModel: DayViewModel
    private lateinit var viewModelFactory: DayViewModel.DayViewModelFactory
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

        val application = requireNotNull(this.activity).application
        val dataSource = ActivitiesDatabase.getInstance(application).activitiesDatabaseDao

        viewModelFactory = DayViewModel.DayViewModelFactory(DayFragmentArgs.fromBundle(requireArguments()).selectedDate, dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DayViewModel::class.java)

        val adapter = ActivityEntryAdapter()

        val dividerItemDecoration = DividerItemDecoration(
            requireActivity(), LinearLayoutManager.VERTICAL
        )
        with(binding.entryList) {
            addItemDecoration(dividerItemDecoration)
            itemAnimator = DefaultItemAnimator()
        }

        binding.entryList.adapter = adapter
        binding.dayViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.addEntry.observe(viewLifecycleOwner, Observer<Boolean> { isClicked ->
            if (isClicked) addNewEntry()
        })

        viewModel.activities.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })


        date = binding.date

        return binding.root
    }

    private fun addNewEntry() {
        val selectedDate = date.text.toString()
        val action = DayFragmentDirections.actionDayFragmentToEntryFragment(selectedDate)
        NavHostFragment.findNavController(this).navigate(action)
    }

}