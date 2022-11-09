package com.example.marketeffectivemobile.app

import android.app.Application
import com.example.market.core.base.BaseViewModel
import com.example.market.list.data.repositories.ProductListRepository
import com.example.market.list.data.rest.RetrofitFactory
import com.example.market.list.di.ProductListModule
import com.example.market.list.ui.screens.ProductListViewModel
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
    private val testModule = module {
        val moduleInstance = ProductListModule()
        single(qualifier = null) { moduleInstance.provideApi(get()) }
        factory(qualifier = null) { ProductListRepository(get()) }
        factory(qualifier = null) { RetrofitFactory() }
        viewModel(qualifier = null) { ProductListViewModel(get()) } bind(BaseViewModel::class)
    }


    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@App)

            modules(
                testModule
            )
        }
    }
}