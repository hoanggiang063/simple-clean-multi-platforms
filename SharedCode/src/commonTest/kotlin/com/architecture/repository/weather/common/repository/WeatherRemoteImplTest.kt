package com.architecture.repository.weather.common.repository

import com.architecture.business.core.exception.TechnicalException
import com.architecture.business.core.exception.UnknownException
import com.architecture.business.weather.info.WeatherInfo
import com.architecture.business.weather.usecase.WeatherRequest
import com.architecture.repository.weather.common.mock.MockService
import com.architecture.repository.weather.repository.WeatherRemoteImpl
import com.architecture.repository.weather.service.WeatherService
import com.jetbrains.handson.mpp.mobile.com.architecture.repository.core.log.Logger
import com.jetbrains.handson.mpp.mobile.com.architecture.repository.core.mapper.BaseExceptionMapperImpl
import coroutines.runTest
import io.ktor.utils.io.errors.*
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue


class WeatherRemoteImplTest {

    lateinit var repository: WeatherRemoteImpl

    val service: WeatherService = MockService()

    @BeforeTest
    fun setUp() {
        repository = WeatherRemoteImpl(service, BaseExceptionMapperImpl(), object : Logger {
            override fun log(error: Throwable) {
                // do nothing
            }

        })
        repository.setParam(WeatherRequest())
    }


    @Test
    fun shouldReturnDataWhenApiSuccess() = runTest {
        val weatherInfoOutPut = repository.invoke()
        assertTrue { weatherInfoOutPut is WeatherInfo }
    }

    @Test
    fun shouldThrowFailWhenApiReturnIoException() {
        runTest {
            (service as MockService).exception = IOException("Io exception")
            try {
                repository.invoke()
            }catch (exception: Throwable){
                assertTrue { exception is TechnicalException }
            }
        }
    }

    @Test
    @Throws(Throwable::class)
    fun shouldThrowFailWhenApiReturnOtherException() {
        runTest {
            (service as MockService).exception = UnknownException()
            try {
                repository.invoke()
            }catch (exception: Throwable){
                assertTrue { exception is UnknownException }
            }
        }
    }
}