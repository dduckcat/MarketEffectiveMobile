package com.example.market.list.ui.screens

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.market.core.base.BaseViewModel
import com.example.market.list.data.models.ProductListModel
import com.example.market.list.data.repositories.ProductListRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class ProductListViewModel (private val repository: ProductListRepository) : BaseViewModel(){

    private var _productsStateFlow = MutableStateFlow(ProductListModel.EMPTY)
    val productsStateFlow: StateFlow<ProductListModel> = _productsStateFlow

    fun getProductList(){
        viewModelScope.launch {
            try {
                _productsStateFlow.value = repository.getProductList()
            } catch (e: Exception){
                Log.e("TAG", "getProductList: Error", )
            }

        }
    }
}