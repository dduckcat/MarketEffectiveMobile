package com.example.market.my_cart.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.lifecycleScope
import com.example.market.cart.databinding.FragmentMyCartBinding
import com.example.market.core.base.BaseFragment
import com.example.market.my_cart.data.models.MyCartModel
import com.example.market.my_cart.ui.adapters.ElemCartAdapter
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.*

class MyCartFragment :
    BaseFragment<FragmentMyCartBinding, MyCartViewModel>(MyCartViewModel::class) {

    private val adapter: ElemCartAdapter
        get() = binding.rvMyCard.adapter as ElemCartAdapter

    override fun getBinding(inflater: LayoutInflater) = FragmentMyCartBinding.inflate(inflater)

    override fun initialize(savedInstanceState: Bundle?) {
        binding.rvMyCard.adapter = ElemCartAdapter()
        observeAPI()
    }

    private fun observeAPI() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.myCartStateFlow.collect {
                fillData(it)
            }
        }
    }

    private fun fillData(data: MyCartModel) = with(data){
        with(binding) {
            adapter.submitList(data.basket)
            tvTotal.text = NumberFormat.getCurrencyInstance(Locale.US).format(total)
            tvDelivery.text = delivery
        }
    }

    companion object {
        fun newInstance() = MyCartFragment()
    }
}