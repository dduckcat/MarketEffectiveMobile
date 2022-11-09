package com.example.marketeffectivemobile.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.market.list.ui.screens.ProductListFragment
import com.example.marketeffectivemobile.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val test = findViewById<FragmentContainerView>(R.id.fragment_container_view)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_view, ProductListFragment.newInstance()).commit()
    }
}