package com.architecture.business.weather.usecase

import com.architecture.business.core.usecase.BaseUseCase
import com.architecture.business.weather.callback.WeatherCallBack
import com.architecture.business.weather.info.WeatherInfo
import com.jetbrains.handson.mpp.mobile.com.architecture.business.core.info.Undefine

interface WeatherUseCase : BaseUseCase<WeatherRequest, WeatherInfo, WeatherCallBack<WeatherInfo>>

data class WeatherRequest(
    var city: String = Undefine.UNDEFINE_STRING,
    var numberDays: Int = Undefine.UNDEFINE_INT,
    var appId: String = Undefine.UNDEFINE_STRING,
    var unit: String = Undefine.DEFAULT_TEMP_UNIT
)