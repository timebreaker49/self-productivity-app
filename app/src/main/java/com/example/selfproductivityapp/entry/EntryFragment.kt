package com.example.selfproductivityapp.entry

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.selfproductivityapp.OnBackPressedListener
import com.example.selfproductivityapp.R
import com.example.selfproductivityapp.database.ActivitiesDatabase
import com.example.selfproductivityapp.database.ActivitiesDay
import com.example.selfproductivityapp.databinding.EntryFragmentBinding
import com.example.selfproductivityapp.day.CHOSEN_ENTRY
import com.example.selfproductivityapp.hideKeyboard


@RequiresApi(Build.VERSION_CODES.O)
class EntryFragment: Fragment(), OnBackPressedListener {

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

        val chosenEntry: ActivitiesDay? = arguments?.getParcelable(CHOSEN_ENTRY)

        val application = requireNotNull(this.activity).application

        val arguments = EntryFragmentArgs.fromBundle(requireArguments()).selectedDate

        val dataSource = ActivitiesDatabase.getInstance(application).activitiesDatabaseDao

        val viewModelFactory = EntryViewModelFactory(arguments,  dataSource, chosenEntry)
        viewModel = ViewModelProvider(this, viewModelFactory).get(EntryViewModel::class.java)

        binding.entryViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.saveEntry.observe(viewLifecycleOwner, Observer { isClicked ->
            if (isClicked) saveEntry()
        })

        date = binding.dateOfEntry

        return binding.root
    }

    private fun saveEntry() {
        viewModel.saveEntry()
        // removes the existing fragment, revealing the date list
        activity?.supportFragmentManager?.beginTransaction()
            ?.remove(this)
            ?.addToBackStack(null)
            ?.commit()
        view?.hideKeyboard()
    }

    override fun onBackPressed() {
        Log.i("baddest", "Show em who!")
        activity?.supportFragmentManager?.beginTransaction()
            ?.remove(this)
            ?.addToBackStack(null)
            ?.commit()
    }
}