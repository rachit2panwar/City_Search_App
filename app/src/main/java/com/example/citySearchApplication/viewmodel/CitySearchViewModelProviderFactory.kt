package com.example.citySearchApplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.citySearchApplication.service.repository.CitySearchRepository

class CitySearchViewModelProviderFactory(private val citySearchRepo: CitySearchRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CitySearchActivityViewModel(citySearchRepo) as T
    }
}