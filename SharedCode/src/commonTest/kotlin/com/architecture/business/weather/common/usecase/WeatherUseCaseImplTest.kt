package com.architecture.business.weather.common.usecase

import com.architecture.business.core.exception.BusinessException
import com.architecture.business.core.exception.TechnicalException
import com.architecture.business.weather.callback.WeatherCallBack
import com.architecture.business.weather.info.WeatherInfo
import com.architecture.business.weather.repository.WeatherRepository
import com.architecture.business.weather.mock.MockWeatherCallBack
import com.architecture.business.weather.mock.MockWeatherRepository
import com.architecture.business.weather.usecase.WeatherRequest
import com.architecture.business.weather.usecase.WeatherUseCaseImpl
import coroutines.runTest
import kotlinx.coroutines.Dispatchers.Unconfined
import kotlin.coroutines.CoroutineContext
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class WeatherUseCaseImplTest {

    lateinit var weatherUseCase: WeatherUseCaseImpl

    var weatherRepository: WeatherRepository = MockWeatherRepository()

    val testContext: CoroutineContext = Unconfined

    @BeforeTest
    fun setUp() {
        weatherUseCase = WeatherUseCaseImpl(weatherRepository)
    }

    @Test
    fun shouldCallbackSuccessWhenRepoReturnSuccessData() = runTest {
        val callback = MockWeatherCallBack()
        excuseTest(callback)
        assertTrue { callback.onCallSuccess }
    }

    @Test
    fun shouldCallbackFailWhenRepoReturnReturnFailByDeFault() = runTest {
        (weatherRepository as MockWeatherRepository).exception = Exception()
        val callback = MockWeatherCallBack()
        excuseTest(callback)
        assertTrue { callback.onCallFail }
    }

    @Test
    fun shouldCallbackFailWhenRepoReturnReturnFailTechnical() = runTest {
        (weatherRepository as MockWeatherRepository).exception = TechnicalException()
        val callback = MockWeatherCallBack()
        excuseTest(callback)
        assertTrue { callback.onCallFail }
    }

    @Test
    fun shouldReturnCityNotFoundWhenRepoReturnReturnBusinessException() = runTest {
        (weatherRepository as MockWeatherRepository).exception = BusinessException()
        val callback = MockWeatherCallBack()
        excuseTest(callback)
        assertTrue { callback.onCallCityNotFound }
    }

    private fun excuseTest(callback: WeatherCallBack<WeatherInfo>) {
        weatherUseCase.buildUseCase(WeatherRequest())
        weatherUseCase.subscriberContextUseCase = testContext
        weatherUseCase.observerContextUseCase = testContext
        weatherUseCase.invoke(callback)
    }
}

