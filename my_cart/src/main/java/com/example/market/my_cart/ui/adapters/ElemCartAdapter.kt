package com.example.market.my_cart.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.market.cart.R
import com.example.market.cart.databinding.ItemCartElemBinding
import com.example.market.core.base.BaseAdapter
import com.example.market.core.extensions.formatTwoNumAfterDot
import com.example.market.my_cart.data.models.MyCartModel
import java.util.*

class ElemCartAdapter : BaseAdapter<MyCartModel.BasketItem, ItemCartElemBinding>(DiffUtilCallback){

    override fun getBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ) = ItemCartElemBinding.inflate(inflater, parent, false)

    override fun bindViewHolder(holder: ViewHolder, data: MyCartModel.BasketItem) {
        holder.binding.apply {
            Glide.with(imProductImage)
                .load(data.images)
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_error)
                .into(imProductImage)
            tvProductName.text = data.title
            tvPrice.text = data.price.formatTwoNumAfterDot(Locale.US)
        }
    }

    object DiffUtilCallback : DiffUtil.ItemCallback<MyCartModel.BasketItem>() {
        override fun areItemsTheSame(oldItem: MyCartModel.BasketItem, newItem: MyCartModel.BasketItem) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: MyCartModel.BasketItem, newItem: MyCartModel.BasketItem) =
            oldItem == newItem
    }
}