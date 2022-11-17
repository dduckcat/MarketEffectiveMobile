package com.example.marketeffectivemobile.app

import android.app.Application
import com.example.market.core.base.BaseViewModel
import com.example.market.core.retrofit_factory.RetrofitFactory
import com.example.market.details.data.repositories.ProductDetailsRepository
import com.example.market.details.di.ProductDetailsModule
import com.example.market.details.ui.screens.ProductDetailsViewModel
import com.example.market.list.data.repositories.ProductListRepository
import com.example.market.list.di.ProductListModule
import com.example.market.list.ui.screens.ProductListViewModel
import com.example.market.my_cart.data.repositories.MyCartRepository
import com.example.market.my_cart.di.MyCartModule
import com.example.market.my_cart.ui.screens.MyCartViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.bind
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private val productListModule = module {
        val moduleInstance = ProductListModule()
        single(qualifier = null) { moduleInstance.provideApi(get()) }
        factory(qualifier = null) { ProductListRepository(get()) }
        factory(qualifier = null) { RetrofitFactory() }
        viewModel(qualifier = null) { ProductListViewModel(get()) } bind (BaseViewModel::class)
    }

    private val productDetailsModule = module {
        val moduleInstance = ProductDetailsModule()
        single(qualifier = null) { moduleInstance.provideApi(get()) }
        factory(qualifier = null) { ProductDetailsRepository(get()) }
        factory(qualifier = null) { RetrofitFactory() }
        viewModel(qualifier = null) { ProductDetailsViewModel(get()) } bind (BaseViewModel::class)
    }

    private val myCartModule = module {
        val moduleInstance = MyCartModule()
        single(qualifier = null) { moduleInstance.provideApi(get()) }
        factory(qualifier = null) { MyCartRepository(get()) }
        factory(qualifier = null) { RetrofitFactory() }
        viewModel(qualifier = null) { MyCartViewModel(get()) } bind (BaseViewModel::class)
    }


    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@App)

            modules(
                productListModule,
                productDetailsModule,
                myCartModule
            )
        }
    }
}