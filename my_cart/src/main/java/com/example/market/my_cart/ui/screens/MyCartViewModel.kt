package com.example.market.my_cart.ui.screens

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.market.core.base.BaseViewModel
import com.example.market.my_cart.data.models.MyCartModel
import com.example.market.my_cart.data.repositories.MyCartRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MyCartViewModel(
    private val repository: MyCartRepository
) : BaseViewModel() {

    private var _myCartStateFlow = MutableStateFlow(MyCartModel.EMPTY)
    val myCartStateFlow: StateFlow<MyCartModel> = _myCartStateFlow

    init {
        getMyCart()
    }

   private fun getMyCart(){
        viewModelScope.launch {
            try {
                _myCartStateFlow.value = repository.getProductDetails()
            } catch (e: Exception){
                Log.e("TAG", "getProductList: Error", )
            }
        }
    }
}