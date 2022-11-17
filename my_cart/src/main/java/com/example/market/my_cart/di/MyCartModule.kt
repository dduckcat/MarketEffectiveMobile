package com.example.market.my_cart.di

import com.example.market.core.retrofit_factory.RetrofitFactory
import com.example.market.my_cart.data.api.MyCartAPI

import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class MyCartModule {

    @Single
    fun provideApi(retrofitFactory: RetrofitFactory): MyCartAPI {
        return retrofitFactory.getApiInterface(MyCartAPI::class.java)
    }
}