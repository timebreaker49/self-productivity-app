package com.example.selfproductivityapp.entry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.selfproductivityapp.R
import com.example.selfproductivityapp.database.ActivitiesDatabase
import com.example.selfproductivityapp.databinding.EntryFragmentBinding

class EntryFragment: Fragment() {

    private lateinit var viewModel: EntryViewModel
    private lateinit var viewModelFactory: EntryViewModelFactory
    private lateinit var binding: EntryFragmentBinding
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

        val application = requireNotNull(this.activity).application

        val dataSource = ActivitiesDatabase.getInstance(application).activitiesDatabaseDao

        val arguments = EntryFragmentArgs.fromBundle(requireArguments()).selectedDate

        val viewModelFactory = EntryViewModelFactory(arguments,  dataSource)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(EntryViewModel::class.java)

        binding.entryViewModel = viewModel

        date = binding.dateOfEntry

        return binding.root
    }
}