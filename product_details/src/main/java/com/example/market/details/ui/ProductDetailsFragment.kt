package com.example.market.details.ui

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.core.content.ContextCompat
import androidx.core.view.setMargins
import com.example.market.core.base.BaseFragment
import com.example.market.details.R
import com.example.market.details.databinding.FragmentProsuctDetailsBinding

class ProductDetailsFragment :
    BaseFragment<FragmentProsuctDetailsBinding, ProductDetailsViewModel>(ProductDetailsViewModel::class) {

    private val listColors = listOf("#010035", "#772D03", "#E10909", "#010035", "#772D03", "#E10909")

    override fun getBinding(inflater: LayoutInflater) =
        FragmentProsuctDetailsBinding.inflate(inflater)

    override fun initialize(savedInstanceState: Bundle?) {
        initColorRadioButtons(listColors)

    }

    private fun initColorRadioButtons(colors: List<String>) {
        colors.forEach { binding.colorsRadioGroup.addView(getColorSelectionRadioButton(it)) }
    }

    private fun getColorSelectionRadioButton(color: String): AppCompatRadioButton {
        val destiny = requireContext().resources.displayMetrics.density
        return AppCompatRadioButton(requireContext()).apply {
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                height = (40 * destiny).toInt()
                width = (40 * destiny).toInt()
                setMargins(20)
            }
            background = createColor(Color.parseColor(color))
            setButtonDrawable(R.drawable.ic_checked)
        }
    }

    private fun createColor(color: Int): GradientDrawable {
        val layerDrawable =
            ContextCompat.getDrawable(requireContext(), R.drawable.circle) as LayerDrawable
        val gradientDrawable =
            layerDrawable.findDrawableByLayerId(R.id.layer_id) as GradientDrawable
        gradientDrawable.setColor(color)
        return gradientDrawable
    }


    companion object {
        fun newInstance() = ProductDetailsFragment()
    }

}