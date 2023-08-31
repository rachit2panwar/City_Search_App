package com.example.citySearchApplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.citySearchApplication.service.model.response.GetSearchCityResponse
import com.example.citySearchApplication.service.repository.CalendarRepository
import com.example.citySearchApplication.utils.Result
import com.example.citySearchApplication.view.adapter.CityItemViews
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

const val USER_NAME = "keep_truckin"
class CitySearchActivityViewModel(private val calendarRepository: CalendarRepository) : ViewModel() {

    private var citySearchList = MutableLiveData<Result<GetSearchCityResponse>>()
    fun observeCitySearchList(): LiveData<Result<GetSearchCityResponse>> {
        return citySearchList
    }

    //for getting Calendar Tasks
    fun getCitySearchList(nameStart : String) = viewModelScope.launch(Dispatchers.IO) {
//        citySearchList.postValue(Result.Loading())
        val response = calendarRepository.getSearchCityList(
            nameStart = nameStart,
            maxRows = 10,
            username = USER_NAME
        )
        citySearchList.postValue(handleCitySearchListResp(response))
    }

    private fun handleCitySearchListResp(response: Response<GetSearchCityResponse>): Result<GetSearchCityResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Result.Success(resultResponse)
            }
        }
        return Result.Error(response.message())
    }

    fun mapResponseToTaskItem(cityResponse: GetSearchCityResponse): List<CityItemViews.CityItem> {
        val listOfCities = arrayListOf<CityItemViews.CityItem>()
        cityResponse.geoNames.forEachIndexed { index, city ->
            listOfCities.add(
                CityItemViews.CityItem(
                    cityName = city.cityName,
                    stateName = city.stateName,
                    countryName = city.countryName,
                )
            )
        }
        return listOfCities
    }
}