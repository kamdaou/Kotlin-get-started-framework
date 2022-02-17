package com.example.kotlinframework

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinframework.database.FrameworkData
import com.example.kotlinframework.databinding.ListItemBinding

class RecyclerViewAdapter(val clickListener: ItemListener): ListAdapter<FrameworkData, RecyclerViewAdapter.ViewHolder>(FrameworkDiffCallback()) {

    class FrameworkDiffCallback: DiffUtil.ItemCallback<FrameworkData>() {
        override fun areItemsTheSame(oldItem: FrameworkData, newItem: FrameworkData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FrameworkData, newItem: FrameworkData): Boolean {
            return oldItem == newItem
        }

    }

    class ViewHolder private constructor(private val binding: ListItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: ItemListener,item: FrameworkData){
            binding.frameworkdata = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding: ListItemBinding =
                    ListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener, item)
    }
}

class ItemListener(val clickListener: (id:Long)->Unit){
    fun onClick(frameworkData: FrameworkData) = clickListener(frameworkData.id)
}