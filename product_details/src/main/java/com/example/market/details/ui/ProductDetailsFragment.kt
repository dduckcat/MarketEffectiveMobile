package com.example.market.details.ui

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.setMargins
import com.example.market.core.base.BaseFragment
import com.example.market.details.R
import com.example.market.details.databinding.FragmentProsuctDetailsBinding

class ProductDetailsFragment :
    BaseFragment<FragmentProsuctDetailsBinding, ProductDetailsViewModel>(ProductDetailsViewModel::class) {

    private val listColors =
        listOf("#010035", "#772D03", "#E10909", "#010035", "#772D03", "#E10909")
    private val listMemories =
        listOf("64 GB", "128 GB", "256 GB", "512 GB", "1 TB")

    override fun getBinding(inflater: LayoutInflater) =
        FragmentProsuctDetailsBinding.inflate(inflater)

    override fun initialize(savedInstanceState: Bundle?) {
        initColorsRadioGroup(listColors)
        initMemoryRadioGroup(listMemories)
    }

    private fun initMemoryRadioGroup(memories: List<String>) {
        memories.forEach { binding.memoryRadioGroup.addView(getMemorySelectionRadioButton(it)) }
    }

    private fun initColorsRadioGroup(colors: List<String>) {
        colors.forEach { binding.colorsRadioGroup.addView(getColorSelectionRadioButton(it)) }
    }

    private fun getMemorySelectionRadioButton(text: String): AppCompatRadioButton {
        val destiny = requireContext().resources.displayMetrics.density
        return AppCompatRadioButton(requireContext()).apply {
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                width = (70 * destiny).toInt()
                height = (30 * destiny).toInt()
                setMargins(15)
                textSize = 13F
                setText(text)
                typeface = ResourcesCompat.getFont(requireContext(), R.font.mark_pro)
                setTextColor(
                    ResourcesCompat.getColorStateList(
                        resources,
                        R.color.selector_text_memory_rb,
                        requireContext().theme
                    )
                )
            }
            gravity = Gravity.CENTER
            setBackgroundResource(R.drawable.selector_memory_checked)
            setButtonDrawable(R.drawable.selector_memory_checked)
        }
    }

    private fun getColorSelectionRadioButton(color: String): AppCompatRadioButton {
        val destiny = requireContext().resources.displayMetrics.density
        return AppCompatRadioButton(requireContext()).apply {
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                width = (40 * destiny).toInt()
                height = (40 * destiny).toInt()
                setMargins(15)
            }
            background = createColor(Color.parseColor(color))
            setButtonDrawable(R.drawable.ic_btn_color_checked)
        }
    }

    private fun createColor(color: Int): GradientDrawable {
        val layerDrawable =
            ContextCompat.getDrawable(requireContext(), R.drawable.bg_color_radio_button) as LayerDrawable
        val gradientDrawable =
            layerDrawable.findDrawableByLayerId(R.id.layer_id) as GradientDrawable
        gradientDrawable.setColor(color)
        return gradientDrawable
    }


    companion object {
        fun newInstance() = ProductDetailsFragment()
    }

}