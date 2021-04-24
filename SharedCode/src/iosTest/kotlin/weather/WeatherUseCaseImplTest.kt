package com.architecture.business.weather

import com.architecture.business.core.exception.BusinessException
import com.architecture.business.core.exception.TechnicalException
import com.architecture.business.core.exception.UnknownException
import com.architecture.business.weather.callback.WeatherCallBack
import com.architecture.business.weather.info.WeatherInfo
import com.architecture.business.weather.repository.WeatherRepository
import com.architecture.business.weather.usecase.WeatherRequest
import com.architecture.business.weather.usecase.WeatherUseCaseImpl
import kotlinx.coroutines.Dispatchers.Unconfined
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext


class WeatherUseCaseImplTest {

}