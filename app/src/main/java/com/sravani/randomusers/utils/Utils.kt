package com.sravani.randomusers.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

object Utils {
    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDob(dobString: String): String {
        // Parse the ISO 8601 date string to an Instant
        val instant = Instant.parse(dobString)
        // Define a formatter for the desired output format and set the system's default time zone
        val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy").withZone(ZoneId.systemDefault())
        return formatter.format(instant)
    }
}