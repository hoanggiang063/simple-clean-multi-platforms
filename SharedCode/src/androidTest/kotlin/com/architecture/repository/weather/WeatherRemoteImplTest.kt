package com.architecture.repository.weather

import com.architecture.business.core.exception.TechnicalException
import com.architecture.business.core.exception.UnknownException
import com.architecture.business.weather.usecase.WeatherRequest
import com.architecture.repository.core.mapper.BaseExceptionMapperImpl
import com.architecture.repository.weather.model.WeatherModel
import com.architecture.repository.weather.repository.WeatherRemoteImpl
import com.architecture.repository.weather.service.WeatherServiceImpl
import com.google.gson.Gson
import com.jetbrains.handson.mpp.mobile.com.architecture.repository.core.log.Logger
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.io.BufferedReader
import java.io.File
import java.io.IOException


@RunWith(MockitoJUnitRunner::class)
class WeatherRemoteImplTest {

    @Rule
    @JvmField
    var expectedException = ExpectedException.none()

    @Mock
    lateinit var repository: WeatherRemoteImpl

    @Mock
    lateinit var service: WeatherServiceImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = WeatherRemoteImpl(service, BaseExceptionMapperImpl(), object: Logger {
            override fun log(error: Throwable) {
                // do nothing
            }

        })
        repository.setParam(WeatherRequest())
    }

    @Test
    fun shouldReturnDataWhenApiSuccess() = runBlocking {
        val file = File(javaClass.classLoader.getResource("test.json").file)
        val bufferedReader: BufferedReader = file.bufferedReader()
        val inputString = bufferedReader.use { it.readText() }
        val gson = Gson()
        val weatherInfoInput = gson.fromJson(inputString, WeatherModel::class.java)
        doReturn(weatherInfoInput).`when`(service).getWeather(any(), any(), any(), any())
        val weatherInfoOutPut = repository.invoke()

        Assert.assertEquals(weatherInfoOutPut.id, weatherInfoInput.city.id)
        // should evaluate more values but no time
    }

    @Test
    @Throws(Throwable::class)
    fun shouldThrowFailWhenApiReturnIoException() {
        expectedException.expect(TechnicalException::class.java)
        runBlocking {
            doAnswer {
                throw IOException()
            }.`when`(service).getWeather(any(), any(), any(), any())
            repository.invoke()
        }
    }

    @Test
    @Throws(Throwable::class)
    fun shouldThrowFailWhenApiReturnOtherException() {
        expectedException.expect(UnknownException::class.java)
        runBlocking {
            doAnswer {
                throw Exception()
            }.`when`(service).getWeather(any(), any(), any(), any())
            repository.invoke()
        }
    }
}