package com.example.selfproductivityapp.day

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.selfproductivityapp.R
import com.example.selfproductivityapp.database.ActivitiesDatabase
import com.example.selfproductivityapp.database.ActivitiesDay
import com.example.selfproductivityapp.databinding.DayFragmentBinding
import com.example.selfproductivityapp.entry.EntryFragment

const val CHOSEN_ENTRY = "chosenEntry"

class DayFragment() : Fragment(), ActivityEntryAdapter.EntryClickListener {

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

        val adapter = ActivityEntryAdapter(this)

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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onEntryClicked(entry: ActivitiesDay) {
        val args = Bundle()
        args.putParcelable(CHOSEN_ENTRY, entry)
        val frag = EntryFragment()
        frag.arguments = args
        Log.i("entryClicked!", "entryId: ${entry.entryId}")

        openEntryFragment(frag)
    }

    private fun openEntryFragment(fragment: EntryFragment) {
        val fragmentManager = activity?.supportFragmentManager
        fragmentManager?.beginTransaction()
            ?.replace(R.id.main_container, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }

    private fun addNewEntry() {
        val selectedDate = date.text.toString()
        val action = DayFragmentDirections.actionDayFragmentToEntryFragment(selectedDate)
        NavHostFragment.findNavController(this).navigate(action)
    }

}