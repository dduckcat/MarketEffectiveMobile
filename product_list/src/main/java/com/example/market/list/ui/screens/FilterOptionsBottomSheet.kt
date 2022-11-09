package com.example.market.list.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.market.list.R
import com.example.market.list.databinding.BtmSheetFiltersBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterOptionsBottomSheet : BottomSheetDialogFragment() {
    private var _binding: BtmSheetFiltersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BtmSheetFiltersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun getTheme() = R.style.CustomBottomSheetDialog

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "ShowBottomSheet"
        fun show(activity: AppCompatActivity) {
            val bottomSheet = FilterOptionsBottomSheet()
            bottomSheet.show(activity.supportFragmentManager, TAG)
        }
    }
}