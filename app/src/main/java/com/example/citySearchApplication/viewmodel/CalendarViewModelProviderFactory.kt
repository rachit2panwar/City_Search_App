package com.example.citySearchApplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.citySearchApplication.service.repository.CalendarRepository

class CalendarViewModelProviderFactory(private val calendarRepository: CalendarRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CitySearchActivityViewModel(calendarRepository) as T
    }
}