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
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.selfproductivityapp.R
import com.example.selfproductivityapp.database.ActivitiesDatabase
import com.example.selfproductivityapp.database.ActivitiesDay
import com.example.selfproductivityapp.databinding.DayFragmentBinding
import com.example.selfproductivityapp.entry.EntryFragment

const val CHOSEN_ENTRY = "chosenEntry"

@RequiresApi(Build.VERSION_CODES.O)
class DayFragment() : Fragment(), ActivityEntryAdapter.EntryClickListener {

    private lateinit var viewModel: DayViewModel
    private lateinit var viewModelFactory: DayViewModel.DayViewModelFactory
    private lateinit var date: TextView
    private val args = Bundle()
    private val frag = EntryFragment()

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
            if (isClicked) openEntryFragment(frag)
        })

        viewModel.activities.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        date = binding.date

        return binding.root
    }

    override fun onEntryClicked(entry: ActivitiesDay) {
        args.putParcelable(CHOSEN_ENTRY, entry)
        frag.arguments = args
        openEntryFragment(frag)
    }

    private fun addDate() { // used to set and pass the date to the next fragment
        frag.arguments = args
        val dateLong = DayFragmentArgs.fromBundle(requireArguments()).selectedDate
        args.putString("selectedDate", dateLong)
    }

    private fun openEntryFragment(fragment: EntryFragment) {
        addDate()
        Log.i("dayToEntry", "hypothetically opening entry fragment")
        val fragmentManager = activity?.supportFragmentManager
        fragmentManager?.beginTransaction()
            ?.replace(R.id.main_container, fragment)
            ?.commit()
    }


}