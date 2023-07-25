package com.example.flowasynchronousexpandeblerecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.example.flowasynchronousexpandeblerecyclerview.databinding.ItemBinding

class ItemAdapter(
    val onClick: OnClick

) : ListAdapter<Person, ItemAdapter.ItemViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemBinding.inflate(inflater, parent, false)
        return ItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val person = getItem(position)

        holder.binding.itemName.text = person.name


        val subListAdapter = SubListAdapter()
        subListAdapter.submitList(person.pets)
        holder.binding.subRecyclerView.adapter = subListAdapter
        val layoutManager = LinearLayoutManager(
            holder.binding.root.context,
            LinearLayoutManager.HORIZONTAL,
            false)
        holder.binding.subRecyclerView.layoutManager = layoutManager

        holder.binding.itemLayout.setOnClickListener {

            onClick.onclick(person)
//            subListAdapter.submitList(person.pets)
            notifyDataSetChanged()
//            notifyItemChanged(position)


            if (holder.binding.subRecyclerView.isVisible) {
                holder.binding.subRecyclerView.visibility = View.GONE
            } else {
                holder.binding.subRecyclerView.visibility = View.VISIBLE
            }
        }

    }


    class DiffCallback : DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem.name == newItem.name
        }
    }

    class ItemViewHolder(var binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)
}