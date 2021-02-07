package com.dammak.mahdi.util

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun String.formatDateTime(): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val inputFormatter = DateTimeFormatter.ofPattern(
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            Locale.ENGLISH
        )
        val outputFormatter = DateTimeFormatter.ofPattern(
            "dd-MM-yyyy HH:mm",
            Locale.ENGLISH
        )
        val date = LocalDateTime.parse(this, inputFormatter)
        outputFormatter.format(date)
    } else {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val outputFormat = SimpleDateFormat("dd-MM-yyyy HH:mm")
        val date = inputFormat.parse(this)
        if (date != null) outputFormat.format(date) else ""
    }
}
