package com.example.selfproductivityapp.day

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.example.selfproductivityapp.R
import com.example.selfproductivityapp.databinding.DayFragmentBinding


class DayFragment : Fragment() {

    private lateinit var viewModel: DayViewModel
    private lateinit var viewModelFactory: DayViewModel.DayViewModelFactory
    private lateinit var binding: DayFragmentBinding

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

        viewModelFactory = DayViewModel.DayViewModelFactory(DayFragmentArgs.fromBundle(arguments!!).selectedDate)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DayViewModel::class.java)

        binding.dayViewModel = viewModel

        return binding.root
    }

}