package com.example.market.details.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.market.core.base.BaseAdapter
import com.example.market.details.R
import com.example.market.details.databinding.ItemProductImageBinding

class ProductImageAdapter : BaseAdapter<String, ItemProductImageBinding>(DiffUtilCallback) {

    override fun getBinding(inflater: LayoutInflater, parent: ViewGroup, viewType: Int) =
        ItemProductImageBinding.inflate(inflater, parent, false)

    override fun bindViewHolder(holder: ViewHolder, data: String) {
        holder.binding.apply {
            Glide.with(imCardBackground)
                .load(data)
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_error)
                .into(imCardBackground)
        }
    }

    object DiffUtilCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String) =
            oldItem.length == newItem.length

        override fun areContentsTheSame(oldItem: String, newItem: String) = oldItem == newItem

    }
}