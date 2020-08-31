package com.example.selfproductivityapp.entry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.selfproductivityapp.R
import com.example.selfproductivityapp.databinding.EntryFragmentBinding

class EntryFragment: Fragment() {

    private lateinit var viewModel: EntryViewModel
    private lateinit var viewModelFactory: EntryViewModel.EntryViewModelFactory
    private lateinit var binding: EntryFragmentBinding

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

        viewModelFactory = EntryViewModel.EntryViewModelFactory(EntryFragmentArgs.fromBundle(arguments!!).selectedDate)
        viewModel = ViewModelProvider(this, viewModelFactory).get(EntryViewModel::class.java)

        return binding.root
    }
}