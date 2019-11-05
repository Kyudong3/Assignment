package com.kyudong.assignment.util

import java.text.SimpleDateFormat
import java.util.*

class DateFormat {
    fun getDateFromString(time: String): Date {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA)
        val date = dateFormat.parse(time)

        return date!!
    }

    fun getMonth(date: Date): String {
        return SimpleDateFormat("MM", Locale.KOREA).format(date)
    }

    fun getDay(date: Date): String {
        return SimpleDateFormat("dd", Locale.KOREA).format(date)
    }

    fun getHour(date: Date): String {
        return SimpleDateFormat("HH", Locale.KOREA).format(date)
    }

    fun getMin(date: Date): String {
        return SimpleDateFormat("mm", Locale.KOREA).format(date)
    }

    fun getCurrentDate(): Date {
        return Date()
    }

}