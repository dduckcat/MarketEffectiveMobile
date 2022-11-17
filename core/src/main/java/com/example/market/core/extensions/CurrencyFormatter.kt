package com.example.market.core.extensions

import java.text.NumberFormat
import java.util.*

fun Double.formatTwoNumAfterDot(locale: Locale): String =
    NumberFormat.getCurrencyInstance(locale).format(this)

fun Double.formatZeroNumAfterDot(locale: Locale): String =
    NumberFormat.getCurrencyInstance().apply {
        maximumFractionDigits = 0
        currency = Currency.getInstance(locale)
    }.format(this)


