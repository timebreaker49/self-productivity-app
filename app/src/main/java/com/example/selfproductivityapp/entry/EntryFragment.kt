package com.example.selfproductivityapp.entry

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.selfproductivityapp.R
import com.example.selfproductivityapp.database.ActivitiesDatabase
import com.example.selfproductivityapp.database.ActivitiesDay
import com.example.selfproductivityapp.databinding.EntryFragmentBinding
import com.example.selfproductivityapp.hideKeyboard


@RequiresApi(Build.VERSION_CODES.O)
class EntryFragment: Fragment() {

    private lateinit var date: TextView
    private lateinit var viewModel: EntryViewModel

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

        // setting variables from day fragment arguments
        val chosenEntry: ActivitiesDay? = EntryFragmentArgs.fromBundle(requireArguments()).chosenEntry
        val arguments = EntryFragmentArgs.fromBundle(requireArguments()).selectedDate
        val dataSource = ActivitiesDatabase.getInstance(application).activitiesDatabaseDao
        val viewModelFactory = EntryViewModelFactory(arguments,  dataSource, chosenEntry)
        viewModel = ViewModelProvider(this, viewModelFactory).get(EntryViewModel::class.java)

        binding.entryViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.saveEntry.observe(viewLifecycleOwner, Observer { isClicked ->
            if (isClicked) {
                if (!isValidEntry()) return@Observer
                saveEntry()
            }
        })

        viewModel.toast.observe(viewLifecycleOwner, Observer { it ->
            it.get()?.let{
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        })

        date = binding.dateOfEntry

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.close_form_button).setOnClickListener {
            navEntryToDayFrag()
        }
    }

    private fun isValidEntry(): Boolean {
        return viewModel.validateEntry()
    }

    private fun saveEntry() {
        viewModel.saveEntry()
        navEntryToDayFrag()
        view?.hideKeyboard()
    }

    private fun navEntryToDayFrag() {
        val destination = EntryFragmentDirections.actionEntryFragmentToDayFragment(date.text.toString())
        NavHostFragment.findNavController(this).navigate(destination)
    }
}