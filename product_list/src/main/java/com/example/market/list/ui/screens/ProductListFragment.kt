package com.example.market.list.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.market.core.base.BaseFragment
import com.example.market.list.R
import com.example.market.list.databinding.CustomTabitemBinding
import com.example.market.list.databinding.FragmentProductListBinding
import com.example.market.list.ui.adapters.BestSellerAdapter
import com.example.market.list.ui.adapters.HotSalesAdapter
import com.example.shared_navigation.navigate
import kotlinx.coroutines.launch

class ProductListFragment :
    BaseFragment<FragmentProductListBinding, ProductListViewModel>(ProductListViewModel::class),
    BestSellerAdapter.BestSellerClickListener, HotSalesAdapter.HotSalesClickListener {
    private val bestSellerAdapter: BestSellerAdapter
        get() = binding.rvBestSeller.adapter as BestSellerAdapter

    private val hotSalesAdapter: HotSalesAdapter
        get() = binding.rvHotSales.adapter as HotSalesAdapter

    override fun getBinding(inflater: LayoutInflater) = FragmentProductListBinding.inflate(inflater)

    override fun initialize(savedInstanceState: Bundle?) {
        initAdapters()
        setupTabItems()
        fillData()
        initBottomSheet()

        binding.imMyCart.setOnClickListener {
            navigate(R.id.action_productListFragment_to_myCartFragment)
        }
    }

    private fun initAdapters() {
        binding.rvBestSeller.adapter = BestSellerAdapter(this)
        binding.rvHotSales.adapter = HotSalesAdapter(this)
        PagerSnapHelper().attachToRecyclerView(binding.rvHotSales)

    }

    private fun initBottomSheet() {
        binding.toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.filterMenuItem) FilterOptionsBottomSheet.show(requireActivity() as AppCompatActivity)
            true
        }
    }

    private fun fillData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.productsStateFlow.collect {
                bestSellerAdapter.submitList(it.best_seller)
                hotSalesAdapter.submitList(it.home_store)
            }
        }
    }

    private fun createCustomTabItem(iconResource: Int, titleResource: Int): View {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.custom_tabitem, null)
        CustomTabitemBinding.bind(view).apply {
            imTabIcon.setImageResource(iconResource)
            tvTabTitle.setText(titleResource)
        }
        return view
    }

    // I had to add it through the code, since there are no indents between the icon and the text
    private fun setupTabItems() = with(binding.tabLayout) {
        getTabAt(0)?.customView = createCustomTabItem(R.drawable.ic_phone, R.string.phones)
        getTabAt(1)?.customView = createCustomTabItem(R.drawable.ic_computer, R.string.computers)
        getTabAt(2)?.customView = createCustomTabItem(R.drawable.ic_health, R.string.health)
        getTabAt(3)?.customView = createCustomTabItem(R.drawable.ic_books, R.string.books)
        getTabAt(4)?.customView = createCustomTabItem(R.drawable.ic_tv, R.string.tv)
    }

    override fun bestSellerClick() {
        navigate(R.id.action_productListFragment_to_productDetailsFragment)
    }

    override fun hotSalesClick() {
        navigate(R.id.action_productListFragment_to_productDetailsFragment)
    }
}