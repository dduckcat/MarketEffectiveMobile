package com.example.market.list.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.market.list.R
import com.example.market.list.data.models.ProductListModel
import com.example.market.list.databinding.ItemHotSalesBinding

class HotSalesAdapter :
    ListAdapter<ProductListModel.HotSalesModel, HotSalesAdapter.BestSellerViewHolder>(
        DiffUtilCallBack
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_hot_sales, parent, false)
        return BestSellerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BestSellerViewHolder, position: Int) {
        val elem = currentList[position]
        holder.bind(elem)
    }

    class BestSellerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemHotSalesBinding.bind(view)

        fun bind(elem: ProductListModel.HotSalesModel) = with(binding) {
            tvTitle.text = elem.title
            tvSubTitle.text = elem.subtitle
            if (elem.is_new) tvIconNew.isVisible = true
            else tvIconNew.isInvisible = true
            Glide.with(imCardBackground).load(elem.picture).into(imCardBackground)
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