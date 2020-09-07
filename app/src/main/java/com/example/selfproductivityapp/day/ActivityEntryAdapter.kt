package com.example.selfproductivityapp.day

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.selfproductivityapp.R
import com.example.selfproductivityapp.TextItemViewHolder
import com.example.selfproductivityapp.database.ActivitiesDay
import com.example.selfproductivityapp.databinding.ListItemEntryBinding

class ActivityEntryAdapter: RecyclerView.Adapter<TextItemViewHolder>() {
    var data = listOf<ActivitiesDay>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val view = layoutInflater.inflate(R.layout.text_item_view, parent, false) as TextView
        return TextItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]

        holder.textView.text = item.description.toString()
    }

    override fun getItemCount() = data.size


}
