package com.architecture.cleanmvvm.weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.architecture.business.core.exception.BusinessException
import com.architecture.business.weather.callback.WeatherCallBack
import com.architecture.business.weather.info.WeatherInfo
import com.architecture.business.weather.usecase.WeatherRequest
import com.architecture.business.weather.usecase.WeatherUseCase
import com.architecture.cleanmvvm.core.configuration.EnvConfiguration

class WeatherViewModel(val weatherUseCase: WeatherUseCase, val envConfiguration: EnvConfiguration) :
    ViewModel() {

    private val _currentWeatherInfo = MutableLiveData<WeatherInfo>()
    var currentWeatherInfo: LiveData<WeatherInfo> = _currentWeatherInfo

    private val _failedException = MutableLiveData<Throwable>()
    var failedException: LiveData<Throwable> = _failedException

    private val _failedByBusiness = MutableLiveData<Throwable>()
    var failedByBusiness: LiveData<Throwable> = _failedByBusiness

    private val _failedByTechnical = MutableLiveData<Throwable>()
    var failedByTechnical: LiveData<Throwable> = _failedByTechnical

    fun loadWeather(searchText: String) {
        val request = WeatherRequest()
        request.city = searchText
        request.appId = envConfiguration.getEnvironmentApiKey()
        request.unit = envConfiguration.getEnvironmentUnit()
        request.numberDays = 7
        weatherUseCase
            .buildUseCase(request)
            .execute(object : WeatherCallBack<WeatherInfo> {
                override fun onCityNotFound(exception: BusinessException) {
                    _failedByBusiness.value = exception
                }

                override fun onSuccess(expectedResult: WeatherInfo?) {
                    _currentWeatherInfo.value = expectedResult
                }

                override fun onFail(throwable: Throwable) {
                    _failedException.value = throwable
                }

//                override fun onFailByTechnical(exception: TechnicalException) {
//                    _failedByTechnical.value = exception
//                }
//
//                override fun onDefaultFail(throwable: Throwable) {
//                    _failedException.value = throwable
//                }

            })
    }
}