package com.architecture.business.weather.usecase

import com.architecture.business.core.exception.BusinessException
import com.architecture.business.core.usecase.BaseUsecaseImpl
import com.architecture.business.weather.callback.WeatherCallBack
import com.architecture.business.weather.info.WeatherInfo
import com.architecture.business.weather.repository.WeatherRepository

class WeatherUseCaseImpl(weatherRepository: WeatherRepository) :
    WeatherUseCase,
    BaseUsecaseImpl<WeatherRequest, WeatherInfo, WeatherCallBack<WeatherInfo>>(weatherRepository) {

    override fun hanldeExceptionByChild(
        error: Throwable,
        callback: WeatherCallBack<WeatherInfo>
    ): Boolean {
        if (error is BusinessException) {
            callback.onCityNotFound(error)
            return true
        }
        return false
    }
}