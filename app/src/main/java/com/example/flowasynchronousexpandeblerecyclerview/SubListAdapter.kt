package com.example.flowasynchronousexpandeblerecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.example.flowasynchronousexpandeblerecyclerview.databinding.ItemChildListBinding

class SubListAdapter(): ListAdapter<Animal, SubListAdapter.SubListViewHolder>(DiffCallback())  {


    class SubListViewHolder (var binding: ItemChildListBinding): RecyclerView.ViewHolder(binding.root)

    class DiffCallback: DiffUtil.ItemCallback<Animal>() {
        override fun areItemsTheSame(oldItem: Animal, newItem: Animal): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: Animal, newItem: Animal): Boolean {
            return oldItem == newItem
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemChildListBinding = ItemChildListBinding.inflate(inflater, parent, false)
        return SubListViewHolder(itemChildListBinding)
    }

    override fun onBindViewHolder(holder: SubListViewHolder, position: Int) {
        val subItem = getItem(position)
        holder.binding.subListText.text =subItem.name
    }


}