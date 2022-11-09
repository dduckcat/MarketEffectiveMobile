package com.example.market.list.di

import com.example.market.list.data.api.ProductListAPI
import com.example.market.list.data.rest.RetrofitFactory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class ProductListModule {

    @Single
    fun provideApi(retrofitFactory: RetrofitFactory): ProductListAPI {
        return retrofitFactory.getApiInterface(ProductListAPI::class.java)
    }
}