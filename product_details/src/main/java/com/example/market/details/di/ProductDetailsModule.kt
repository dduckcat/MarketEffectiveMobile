package com.example.market.details.di

import com.example.market.core.retrofit_factory.RetrofitFactory
import com.example.market.details.data.api.ProductDetailsAPI
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class ProductDetailsModule {

    @Single
    fun provideApi(retrofitFactory: RetrofitFactory): ProductDetailsAPI {
        return retrofitFactory.getApiInterface(ProductDetailsAPI::class.java)
    }
}