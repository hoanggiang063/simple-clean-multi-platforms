package com.architecture.business.weather.mock

import com.architecture.business.core.exception.BusinessException
import com.architecture.business.weather.callback.WeatherCallBack
import com.architecture.business.weather.info.WeatherInfo
import com.architecture.business.weather.repository.WeatherRepository
import com.architecture.business.weather.usecase.WeatherRequest

class MockWeatherCallBack: WeatherCallBack<WeatherInfo> {
    var onCallSuccess = false
    var onCallCityNotFound = false
    var onCallFail = false;

    override fun onCityNotFound(exception: BusinessException) {
        onCallCityNotFound = true;
    }

    override fun onSuccess(expectedResult: WeatherInfo?) {
        onCallSuccess = true
    }

    override fun onFail(throwable: Throwable) {
        onCallFail = true;
    }

}

class MockWeatherRepository: WeatherRepository {

    var exception: Throwable? = null

    override fun setParam(param: WeatherRequest) {
    }

    override suspend fun invoke(): WeatherInfo {
        if (exception == null) {
            return WeatherInfo()
        } else {
            throw exception as Throwable
        }
    }
}