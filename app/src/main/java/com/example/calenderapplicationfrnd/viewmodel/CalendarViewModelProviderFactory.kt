package com.example.calenderapplicationfrnd.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.calenderapplicationfrnd.service.repository.CalendarRepository

class CalendarViewModelProviderFactory(private val calendarRepository: CalendarRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CalendarActivityViewModel(calendarRepository) as T
    }
}