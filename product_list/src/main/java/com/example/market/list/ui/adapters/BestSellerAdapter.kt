package com.example.market.list.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.market.list.R
import com.example.market.list.data.models.ProductListModel
import com.example.market.list.databinding.ItemBestSellerBinding

class BestSellerAdapter :
    ListAdapter<ProductListModel.BestSellerModel, BestSellerAdapter.BestSellerViewHolder>(
        DiffUtilCallBack
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_best_seller, parent, false)
        return BestSellerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BestSellerViewHolder, position: Int) {
        val elem = currentList[position]
        holder.bind(elem)
    }

    class BestSellerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemBestSellerBinding.bind(view)

        fun bind(elem: ProductListModel.BestSellerModel) = with(binding) {
            tvPriceDiscount.text = elem.price_without_discount.toString()
            tvPriceWithoutDiscount.text = elem.discount_price.toString()
            tvTitle.text = elem.title
            imFavorite.setImageResource(
                if (elem.is_favorites) R.drawable.ic_like_active
                else R.drawable.ic_like_inactive
            )
            Glide.with(imCardBackground)
                .load(elem.picture)
                .into(imCardBackground)
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
}