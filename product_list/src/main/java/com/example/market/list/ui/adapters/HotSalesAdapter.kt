package com.example.market.list.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.market.core.base.BaseAdapter
import com.example.market.list.data.models.ProductListModel
import com.example.market.list.databinding.ItemHotSalesBinding

class HotSalesAdapter :
    BaseAdapter<ProductListModel.HotSalesModel, ItemHotSalesBinding>(DiffUtilCallBack) {

    override fun getBinding(
        inflater: LayoutInflater, parent: ViewGroup, viewType: Int
    ) = ItemHotSalesBinding.inflate(inflater, parent, false)

    override fun bindViewHolder(holder: ViewHolder, data: ProductListModel.HotSalesModel) {
        holder.binding.apply {
            tvTitle.text = data.title
            tvSubTitle.text = data.subtitle
            if (data.is_new) tvIconNew.isVisible = true
            else tvIconNew.isInvisible = true
            Glide.with(imCardBackground).load(data.picture).into(imCardBackground)
        }
    }

    private object DiffUtilCallBack : DiffUtil.ItemCallback<ProductListModel.HotSalesModel>() {
        override fun areItemsTheSame(
            oldItem: ProductListModel.HotSalesModel, newItem: ProductListModel.HotSalesModel
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: ProductListModel.HotSalesModel, newItem: ProductListModel.HotSalesModel
        ) = oldItem == newItem
    }
}