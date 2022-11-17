package com.example.market.core.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import org.koin.androidx.viewmodel.ext.android.viewModelForClass
import kotlin.reflect.KClass

@SuppressLint("CheckResult")
abstract class BaseFragment<V : ViewBinding, VM : BaseViewModel>(clazz: KClass<VM>) : Fragment() {

    private var _binding: V? = null
    protected val binding get() = _binding!!
    protected abstract fun getBinding(inflater: LayoutInflater): V

    protected val viewModel: VM by viewModelForClass(clazz)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = getBinding(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize(savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    abstract fun initialize(savedInstanceState: Bundle?)
}