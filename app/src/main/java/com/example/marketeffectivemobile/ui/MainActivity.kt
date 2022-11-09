package com.example.marketeffectivemobile.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.market.list.ui.screens.ProductListFragment
import com.example.marketeffectivemobile.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, ProductListFragment.newInstance())
            .commit()
    }
}