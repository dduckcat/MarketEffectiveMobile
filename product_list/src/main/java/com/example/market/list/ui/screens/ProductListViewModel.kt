package com.example.market.list.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.market.list.data.models.ProductListModel
import com.example.market.list.data.repositories.ProductListRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class ProductListViewModel (private val repository: ProductListRepository) : ViewModel(){

    private var _productsStateFlow = MutableStateFlow(ProductListModel.EMPTY)
    val productsStateFlow: StateFlow<ProductListModel> = _productsStateFlow

    fun getProductList(){
        viewModelScope.launch {
            _productsStateFlow.value = repository.getProductList()
        }
    }
}