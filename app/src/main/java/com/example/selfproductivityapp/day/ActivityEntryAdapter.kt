package com.example.selfproductivityapp.day

import android.os.Build
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

class ActivityEntryAdapter: androidx.recyclerview.widget.ListAdapter<ActivitiesDay, ActivityEntryAdapter.ViewHolder>(EntryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder private constructor(val binding: ListItemEntryBinding) : RecyclerView.ViewHolder(binding.root) {

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(
            item: ActivitiesDay
        ) {
            val res = itemView.context.resources
//            binding.entry = item
//            binding.executePendingBindings()
            binding.listStartTime.text = convertEpochToTimeFormatted(item.startTimeMilli)
            binding.listEndTime.text = convertEpochToTimeFormatted(item.endTimeMilli)
            binding.listDescriptionText.text = item.description
            binding.duration.text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)
            binding.category.text = item.category
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemEntryBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
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