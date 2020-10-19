package com.example.selfproductivityapp.day

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.selfproductivityapp.convertDurationToFormatted
import com.example.selfproductivityapp.convertEpochToTimeFormatted
import com.example.selfproductivityapp.database.ActivitiesDay
import com.example.selfproductivityapp.databinding.ListItemEntryBinding
import com.example.selfproductivityapp.entry.EntryFragment

class ActivityEntryAdapter(private val mListener: EntryClickListener) : androidx.recyclerview.widget.ListAdapter<ActivitiesDay, ActivityEntryAdapter.ViewHolder>(EntryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, mListener)
    }

    class ViewHolder private constructor(private val binding: ListItemEntryBinding) : RecyclerView.ViewHolder(binding.root) {

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(item: ActivitiesDay, clickListener: EntryClickListener) {
            binding.entry = item
            binding.chosenEntry = clickListener
            binding.listDescriptionText.text = item.description
            binding.category.text = item.category
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemEntryBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    interface EntryClickListener {
        fun navDayToEntryFrag(chosenEntry: ActivitiesDay?)
    }
}

class EntryDiffCallback : DiffUtil.ItemCallback<ActivitiesDay>() {
    override fun areItemsTheSame(oldItem: ActivitiesDay, newItem: ActivitiesDay): Boolean {
        return oldItem.entryId == newItem.entryId
    }

    override fun areContentsTheSame(oldItem: ActivitiesDay, newItem: ActivitiesDay): Boolean {
        return oldItem == newItem
    }

}