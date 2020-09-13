package com.example.selfproductivityapp.entry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.InverseBindingListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.selfproductivityapp.R
import com.example.selfproductivityapp.database.ActivitiesDatabase
import com.example.selfproductivityapp.database.ActivitiesDay
import com.example.selfproductivityapp.databinding.EntryFragmentBinding
import com.example.selfproductivityapp.day.CHOSEN_ENTRY

class EntryFragment: Fragment() {

    private lateinit var date: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: EntryFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.entry_fragment,
            container, false
        )

        val chosenEntry: ActivitiesDay? = arguments?.getParcelable(CHOSEN_ENTRY)

        val application = requireNotNull(this.activity).application

        val arguments = EntryFragmentArgs.fromBundle(requireArguments()).selectedDate

        val dataSource = ActivitiesDatabase.getInstance(application).activitiesDatabaseDao

        val viewModelFactory = EntryViewModelFactory(arguments,  dataSource, chosenEntry)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(EntryViewModel::class.java)

        binding.entryViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        date = binding.dateOfEntry

        return binding.root
    }
}