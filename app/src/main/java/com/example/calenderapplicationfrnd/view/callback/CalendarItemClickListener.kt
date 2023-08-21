package com.example.calenderapplicationfrnd.view.callback

import com.example.calenderapplicationfrnd.view.adapter.CalendarItemViews

interface CalendarItemClickListener {

    fun onCalendarItemClicked(item: CalendarItemViews.CalendarDate)
}