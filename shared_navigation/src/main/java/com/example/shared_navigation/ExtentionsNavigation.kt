package com.example.shared_navigation

import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import java.io.Serializable

fun Fragment.navigate(actionId: Int, hostId: Int? = null, data: Serializable? = null) {
    val navController = hostId?.let { Navigation.findNavController(requireActivity(), it) }
            ?: findNavController()

    navController.navigate(actionId)
}

fun Fragment.popBackStack(hostId: Int? = null) {
    val navController = hostId?.let { Navigation.findNavController(requireActivity(), it) }
        ?: findNavController()

    navController.popBackStack()
}