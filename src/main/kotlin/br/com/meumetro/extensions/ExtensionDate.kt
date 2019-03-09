package br.com.meumetro.extensions

import java.text.SimpleDateFormat
import java.util.*
import java.util.Locale

fun Date.getYearFormatted(): Int {
    val format = SimpleDateFormat("yyyy", Locale.getDefault())
    return format.format(this).toInt()
}

fun Date.getMonthFormatted(): Int {
    val format = SimpleDateFormat("MM", Locale.getDefault())
    return format.format(this).toInt()
}

fun Date.getDayFormatted(): Int {
    val format = SimpleDateFormat("dd", Locale.getDefault())
    return format.format(this).toInt()
}

fun Date.getHoursFormatted(): Int {
    val format = SimpleDateFormat("HH", Locale.getDefault())
    return format.format(this).toInt()
}

fun Date.getMinutesFormatted(): Int {
    val format = SimpleDateFormat("mm", Locale.getDefault())
    return format.format(this).toInt()
}

fun Date.getSecondsFormatted(): Int {
    val format = SimpleDateFormat("ss", Locale.getDefault())
    return format.format(this).toInt()
}