package com.example.market.list.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.market.list.R
import com.example.market.list.databinding.CustomTabitemBinding
import com.example.market.list.databinding.FragmentProductListBinding
import com.example.market.list.ui.adapters.BestSellerAdapter
import com.example.market.list.ui.adapters.HotSalesAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductListFragment : Fragment() {

    private val bestSellerAdapter: BestSellerAdapter
        get() = binding.rvBestSeller.adapter as BestSellerAdapter

    private val hotSalesAdapter: HotSalesAdapter
        get() = binding.rvHotSales.adapter as HotSalesAdapter

    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvBestSeller.adapter = BestSellerAdapter()
        binding.rvHotSales.adapter = HotSalesAdapter()
        setupTabItems()

        viewModel.getProductList()
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = ProductListFragment()
    }
}