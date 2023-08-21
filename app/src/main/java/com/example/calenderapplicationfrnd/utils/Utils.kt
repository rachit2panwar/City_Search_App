package com.example.calenderapplicationfrnd.utils


import android.view.*
import com.example.calenderapplicationfrnd.view.adapter.CalendarItemViews
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

inline fun View.debouncedOnClick(debounceTill: Long = 500, crossinline onClick: (v: View) -> Unit) {
    this.setOnClickListener(object : DebouncedOnClickListener(debounceTill) {
        override fun onDebouncedClick(v: View) {
            onClick(v)
        }
    })
}

object Utils {
    fun daysInMonthArray(date: LocalDate): List<CalendarItemViews.CalendarDate> {
        val daysInMonthArray = ArrayList<CalendarItemViews.CalendarDate>()
        val yearMonth = YearMonth.from(date)
        val daysInMonth = yearMonth.lengthOfMonth()
        val firstOfMonth = date?.withDayOfMonth(1)
        val dayOfWeek = (firstOfMonth?.dayOfWeek?.value ?: 1) % 7

        val previousMonthLastDay = firstOfMonth?.minusDays(1)?.dayOfMonth ?: 1
        val nextMonthFirstDay = firstOfMonth?.plusMonths(1)?.dayOfMonth ?: 1

        var addedPreviousMonthDates = 0
        var addedCurrentMonthDates = 0

        for (i in 1..42) {
            if (i <= dayOfWeek) {
                val day = previousMonthLastDay - dayOfWeek + i
                daysInMonthArray.add(CalendarItemViews.CalendarDate(day.toString(), "#808080"))
                addedPreviousMonthDates++
            } else if (i > daysInMonth + dayOfWeek) {
                if ((daysInMonth + dayOfWeek) <= 35 && i >= 36) {
                    break
                }
                val day = nextMonthFirstDay + addedCurrentMonthDates
                daysInMonthArray.add(CalendarItemViews.CalendarDate(day.toString(), "#808080"))
                addedCurrentMonthDates++
            } else {
                daysInMonthArray.add(
                    CalendarItemViews.CalendarDate(
                        (i - dayOfWeek).toString(),
                        "#FF000000"
                    )
                )
            }
        }
        return daysInMonthArray
    }

    fun monthYearFromDate(date: LocalDate?): String? {
        val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        return date?.format(formatter)
    }
}