package com.logan.lowesweather_loganmetzger.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.logan.lowesweather_loganmetzger.models.HourlyResponseDTO
import com.logan.lowesweather_loganmetzger.models.WeatherResponseDTO
import com.logan.lowesweather_loganmetzger.repositories.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private var _weather: MutableLiveData<WeatherResponseDTO> = MutableLiveData()
    val weather: LiveData<WeatherResponseDTO> get() = _weather

    // Holds value of selected weather item pressed by user
    private var _selectedWeatherItem: MutableLiveData<HourlyResponseDTO> = MutableLiveData()
    val selectedWeatherItm: LiveData<HourlyResponseDTO> get() = _selectedWeatherItem

    // Holds boolean value for handling navigation once
    private var _shouldNavigate: MutableLiveData<Boolean> = MutableLiveData()
    val shouldNavigate: LiveData<Boolean> get() = _shouldNavigate

    // Holds String value for handling search errors
    private var _errorMsg: MutableLiveData<String> = MutableLiveData()
    val errorMsg: LiveData<String> get() = _errorMsg

    // Holds value for handling progress visibility
    private var _progressIsVisible: MutableLiveData<Boolean> = MutableLiveData()
    val progressIsVisible: LiveData<Boolean> get() = _progressIsVisible

    // Holds value for search query
    var cityName = ""
        get() = field.toUpperCase()

    fun getWeather(city: String) {
        if (city.isBlank().not()) {
            cityName = city
            _progressIsVisible.value = true
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val response = WeatherRepository.getWeather(city)
                    if (response.body() != null && response.isSuccessful) {
                        _shouldNavigate.postValue(true)
                        _weather.postValue(response.body()!!)
                        _errorMsg.postValue("")
                    } else {
                        _errorMsg.postValue("No weather results")
                    }
                    _progressIsVisible.postValue(false)
                } catch (e: Exception) {
                    _progressIsVisible.postValue(false)
                    _errorMsg.postValue(e.message)
                }
            }
        } else {
            _errorMsg.value = "Search cannot be empty"
        }
    }

    fun setShouldNavigate(shouldNavigate: Boolean) {
        _shouldNavigate.value = shouldNavigate
    }

    fun setUserSelectedWeatherItem(hourlyWeather: HourlyResponseDTO) {
        _selectedWeatherItem.value = hourlyWeather
    }
}