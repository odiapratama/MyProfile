package com.problemsolver.core.utils

import java.text.SimpleDateFormat
import java.util.Locale

const val ISO_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
const val DATE_MONTH_YEAR_FORMAT = "dd MMMM yyyy"

fun String.formatDate(inputFormat: String, outputFormat: String): String {
    val sdf = SimpleDateFormat(inputFormat, Locale.getDefault())
    val date = sdf.parse(this)
    val result = SimpleDateFormat(outputFormat, Locale.getDefault())
    return if (date != null) {
        result.format(date)
    } else {
        this
    }
}