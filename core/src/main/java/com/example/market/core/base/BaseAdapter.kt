package com.example.market.core.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<T : Any, V : ViewBinding>(diffUtil: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, BaseAdapter<T, V>.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(getBinding(LayoutInflater.from(parent.context), parent, viewType))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val elem = currentList[position]
            bindViewHolder(holder, elem)
    }

    abstract fun getBinding(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): V
    abstract fun bindViewHolder(holder: ViewHolder, data: T)

    inner class ViewHolder(val binding: V) : RecyclerView.ViewHolder(binding.root)
}
