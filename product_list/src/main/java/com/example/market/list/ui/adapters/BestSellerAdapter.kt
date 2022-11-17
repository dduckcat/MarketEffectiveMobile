package com.example.market.list.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.market.core.base.BaseAdapter
import com.example.market.core.extensions.formatZeroNumAfterDot
import com.example.market.list.R
import com.example.market.list.data.models.ProductListModel
import com.example.market.list.databinding.ItemBestSellerBinding
import java.util.*

class BestSellerAdapter(private val clickListener: BestSellerClickListener) :
    BaseAdapter<ProductListModel.BestSellerModel, ItemBestSellerBinding>(DiffUtilCallBack) {

    override fun getBinding(
        inflater: LayoutInflater, parent: ViewGroup, viewType: Int
    ) = ItemBestSellerBinding.inflate(inflater, parent, false)

    override fun bindViewHolder(holder: ViewHolder, data: ProductListModel.BestSellerModel) {
        holder.binding.apply {


            tvPriceDiscount.text = data.price_without_discount.formatZeroNumAfterDot(Locale.US)
            tvPriceWithoutDiscount.text = data.discount_price.formatZeroNumAfterDot(Locale.US)
            tvTitle.text = data.title
            imFavorite.setImageResource(
                if (data.is_favorites) R.drawable.ic_like_active
                else R.drawable.ic_like_inactive
            )
            Glide.with(imCardBackground).load(data.picture).error(R.drawable.ic_error)
                .placeholder(R.drawable.ic_loading).into(imCardBackground)
        }
        holder.itemView.setOnClickListener {
            clickListener.bestSellerClick()
        }
    }

    private object DiffUtilCallBack : DiffUtil.ItemCallback<ProductListModel.BestSellerModel>() {

        override fun areItemsTheSame(
            oldItem: ProductListModel.BestSellerModel, newItem: ProductListModel.BestSellerModel
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: ProductListModel.BestSellerModel, newItem: ProductListModel.BestSellerModel
        ) = oldItem == newItem
    }

    interface BestSellerClickListener {
        fun bestSellerClick()
    }
}
