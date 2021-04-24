package com.architecture.business.weather.usecase

import com.architecture.business.core.exception.BusinessException
import com.architecture.business.core.exception.TechnicalException
import com.architecture.business.core.exception.UnknownException
import com.architecture.business.weather.callback.WeatherCallBack
import com.architecture.business.weather.info.WeatherInfo
import com.architecture.business.weather.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers.Unconfined
import kotlin.coroutines.CoroutineContext
import kotlin.test.BeforeTest
import kotlin.test.Test

class WeatherUseCaseImplTest {

//    lateinit var weatherUseCase: WeatherUseCaseImpl
//
//    internal fun <T> runTest(block: suspend () -> T) {}
//
//    var weatherRepository: WeatherRepository = object: WeatherRepository{
//        override fun setParam(param: WeatherRequest) {
//            // do nothing
//        }
//
//        override suspend fun invoke(): WeatherInfo {
//            return WeatherInfo()
//        }
//
//    }
//
//    val testContext: CoroutineContext = Unconfined
//
//    @BeforeTest
//    fun setUp() {
//        weatherUseCase = WeatherUseCaseImpl(weatherRepository)
//    }
//
//    @Test
//    fun shouldCallbackSuccessWhenRepoReturnSuccessData() = runTest {
//        val callback = mockCallback<WeatherInfo>()
//        excuseTest(callback)
//        verify(callback).onSuccess(ArgumentMatchers.isA(WeatherInfo::class.java))
//    }
//
//    @Test
//    fun shouldCallbackFailByDeFaultWhenRepoReturnReturnFailByDeFault() = runTest {
//        doAnswer {
//            throw UnknownException()
//        }.`when`(weatherRepository).invoke()
//        val callback = mockCallback<WeatherInfo>()
//        excuseTest(callback)
//        verify(callback).onDefaultFail(any())
//    }
//
//    @Test
//    fun shouldCallbackFailByTechnicalWhenRepoReturnReturnFailTechnical() = runTest {
//        doAnswer {
//            throw TechnicalException()
//        }.`when`(weatherRepository).invoke()
//        val callback = mockCallback<WeatherInfo>()
//        excuseTest(callback)
//        verify(callback).onFailByTechnical(any())
//    }
//
//    @Test
//    fun shouldReturnCityNotFoundWhenRepoReturnReturnFailTechnical() = runTest {
//        doAnswer {
//            throw BusinessException()
//        }.`when`(weatherRepository).invoke()
//        val callback = mockCallback<WeatherInfo>()
//        excuseTest(callback)
//        verify(callback).onCityNotFound(any())
//    }
//
//    private fun excuseTest(callback: WeatherCallBack<WeatherInfo>) {
//        weatherUseCase.buildUseCase(WeatherRequest())
//        weatherUseCase.subscriberContext = testContext
//        weatherUseCase.observerContext = testContext
//        weatherUseCase.invoke(callback)
//    }
//
//    private fun <T> mockCallback(): WeatherCallBack<T> {
//        return Mockito.mock(WeatherCallBack::class.java) as WeatherCallBack<T>
//    }
}