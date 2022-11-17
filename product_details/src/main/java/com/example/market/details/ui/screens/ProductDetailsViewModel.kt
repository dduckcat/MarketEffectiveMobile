package com.example.market.details.ui.screens

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.market.core.base.BaseViewModel
import com.example.market.details.data.models.ProductDetailsModel
import com.example.market.details.data.repositories.ProductDetailsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductDetailsViewModel(
    private val repository: ProductDetailsRepository
) : BaseViewModel() {

    private var _detailsStateFlow = MutableStateFlow(ProductDetailsModel.EMPTY)
    val detailsStateFlow: StateFlow<ProductDetailsModel> = _detailsStateFlow

    init {
        getProductDetails()
    }

   private fun getProductDetails(){
        viewModelScope.launch {
            try {
                _detailsStateFlow.value = repository.getProductDetails()
            } catch (e: Exception){
                Log.e("TAG", "getProductList: Error", )
            }
        }
    }
}