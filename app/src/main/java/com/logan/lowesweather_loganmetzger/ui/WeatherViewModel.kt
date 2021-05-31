package com.logan.lowesweather_loganmetzger.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.logan.lowesweather_loganmetzger.models.WeatherResponseDTO
import com.logan.lowesweather_loganmetzger.repositories.WeatherRepository
import com.logan.lowesweather_loganmetzger.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val weatherRepository: WeatherRepository by lazy {
        WeatherRepository()
    }

    private val _query = MutableLiveData<String>()
    private val _hasNavigated = MutableLiveData<Boolean>()

    private var _weather: MutableLiveData<Resource<WeatherResponseDTO>> = MutableLiveData()
    val weather: LiveData<Resource<WeatherResponseDTO>> get() = _weather

    fun getWeather(city: String) {
        _weather.value = Resource.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val weather = weatherRepository.getWeather(city)
                val resource = if (weather.body() != null) Resource.Success(weather.body()!!)
                else Resource.Error("No weather results")

                _weather.postValue(
                    resource
                )
            } catch (e: Exception) {
                _weather.postValue(
                    Resource.Error(e.toString())
                )
            }
        }
    }

    var query: String = ""
        set(value) {
            _query.value = value
            field = value
        }

    var hasNavigated: Boolean = false
        set(value) {
            _hasNavigated.value = value
            field = value
        }
}