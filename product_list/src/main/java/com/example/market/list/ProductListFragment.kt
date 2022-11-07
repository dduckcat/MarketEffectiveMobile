package com.example.market.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.market.list.databinding.CustomTabitemBinding
import com.example.market.list.databinding.FragmentProductListBinding

class ProductListFragment : Fragment() {

    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTabItems()
    }

    private fun createCustomTabItem(iconResource: Int, titleResource: Int): View {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.custom_tabitem, null)
        CustomTabitemBinding.bind(view).apply {
            imTabIcon.setImageResource(iconResource)
            tvTabTitle.setText(titleResource)
        }
        return view
    }

    //I had to add it through the code, since there are no indents between the icon and the text
    private fun setupTabItems() = with(binding) {
        tabLayout.getTabAt(0)?.customView = createCustomTabItem(R.drawable.ic_phone, R.string.phones)
        tabLayout.getTabAt(1)?.customView = createCustomTabItem(R.drawable.ic_computer, R.string.computers)
        tabLayout.getTabAt(2)?.customView = createCustomTabItem(R.drawable.ic_health, R.string.health)
        tabLayout.getTabAt(3)?.customView = createCustomTabItem(R.drawable.ic_books, R.string.books)
        tabLayout.getTabAt(4)?.customView = createCustomTabItem(R.drawable.ic_tv, R.string.tv)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = ProductListFragment()
    }
}