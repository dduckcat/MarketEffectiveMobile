package com.example.market.details.ui.screens

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
import androidx.lifecycle.lifecycleScope
import com.example.market.core.base.BaseFragment
import com.example.market.details.R
import com.example.market.details.data.models.ProductDetailsModel
import com.example.market.details.databinding.FragmentProsuctDetailsBinding
import com.example.market.details.ui.adapters.ProductImageAdapter
import com.example.shared_navigation.navigate
import com.example.shared_navigation.popBackStack
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.*

class ProductDetailsFragment :
    BaseFragment<FragmentProsuctDetailsBinding, ProductDetailsViewModel>(ProductDetailsViewModel::class) {

    private val adapter: ProductImageAdapter
        get() = binding.rvProductImage.adapter as ProductImageAdapter

    override fun getBinding(inflater: LayoutInflater) =
        FragmentProsuctDetailsBinding.inflate(inflater)

    override fun initialize(savedInstanceState: Bundle?) {
        binding.rvProductImage.apply {
            adapter = ProductImageAdapter()
            setInfinite(true)
            setIntervalRatio(0.8f)
        }

        observeAPI()

        binding.imMyCart.setOnClickListener {
            navigate(R.id.action_productDetailsFragment_to_myCartFragment)
        }

        binding.imGoBack.setOnClickListener {
            popBackStack()
        }
    }

    private fun observeAPI() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.detailsStateFlow.collect {
                fillLayout(it)

            }
        }
    }

    private fun fillLayout(data: ProductDetailsModel) = with(data) {
        with(binding) {
            tvProductName.text = title
            tvCPU.text = CPU
            tvCamera.text = camera
            tvRAM.text = ssd
            tvSD.text = sd
            tvPrice.text = NumberFormat.getCurrencyInstance(Locale.US).format(price)
            ratingBar.rating = rating.toFloat()
            imLike.setImageResource(
                if (isFavorites) R.drawable.ic_fill_like else R.drawable.ic_like
            )
        }
        adapter.submitList(images)
        initColorsRadioGroup(color)
        initMemoryRadioGroup(capacity)
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
                val text2 = "$text GB"
                setText(text2)
                typeface = ResourcesCompat.getFont(requireContext(), R.font.mark_pro)
                setTextColor(
                    ResourcesCompat.getColorStateList(
                        resources, R.color.selector_text_memory_rb, requireContext().theme
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
        val layerDrawable = ContextCompat.getDrawable(
            requireContext(), R.drawable.bg_color_radio_button
        ) as LayerDrawable
        val gradientDrawable =
            layerDrawable.findDrawableByLayerId(R.id.layer_id) as GradientDrawable
        gradientDrawable.setColor(color)
        return gradientDrawable
    }
}