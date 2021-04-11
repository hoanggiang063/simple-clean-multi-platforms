package com.architecture.cleanmvvm.weather.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.architecture.business.demo.callback.ToDoCallBack
import com.architecture.business.demo.info.ToDoInfo
import com.architecture.business.demo.repository.ToDoRepository
import com.architecture.business.demo.usecase.ToDoUseCase
import com.architecture.business.demo.usecase.ToDoUseCaseImpl
import com.architecture.business.weather.info.WeatherInfo
import com.architecture.business.weather.usecase.WeatherRequest
import com.architecture.business.weather.usecase.WeatherUseCase
import com.architecture.cleanmvvm.core.configuration.EnvConfiguration
import com.architecture.repository.core.mapper.BaseExceptionMapperImpl
import com.architecture.repository.demo.remote.features.todo.repository.RemoteToDoRepositoryImpl
import com.architecture.repository.demo.service.Webservice
import com.jetbrains.handson.mpp.mobile.com.architecture.repository.core.log.Logger
import com.jetbrains.handson.mpp.mobile.com.architecture.repository.core.service.PlatformService.httpClientEngine
import com.jetbrains.handson.mpp.mobile.com.architecture.repository.demo.remote.features.todo.service.WebServiceImpl
import kotlinx.coroutines.Job

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

        var webservice: Webservice = WebServiceImpl(httpClientEngine);

        var toDoRepository: ToDoRepository =
            RemoteToDoRepositoryImpl(webservice, BaseExceptionMapperImpl(),
                object : Logger {
                    override fun log(error: Throwable) {
                        Log.v("vhgiang", "error:" + error?.printStackTrace())
                    }

                }
            )
        var todoUseCase: ToDoUseCase = ToDoUseCaseImpl(toDoRepository)

        todoUseCase.buildUseCase(1)
        var job: Job = todoUseCase.execute(object : ToDoCallBack {
            override fun onSuccess(expectedResult: ToDoInfo?) {
                Log.v("vhgiang", "=> remote solution run pass: " + expectedResult.toString())
            }

            override fun onFail(throwable: Throwable) {
                Log.v("vhgiang", "=> remote solution run fail: " + throwable?.message)
            }
        })

//        weatherUseCase
//            .buildUseCase(request)
//            .execute(object : WeatherCallBack<WeatherInfo> {
//                override fun onCityNotFound(exception: BusinessException) {
//                    _failedByBusiness.value = exception
//                }
//
//                override fun onSuccess(expectedResult: WeatherInfo?) {
//                    _currentWeatherInfo.value = expectedResult
//                }
//
//                override fun onFail(throwable: Throwable) {
//                    _failedException.value = throwable
//                }
//
//            })
    }
}