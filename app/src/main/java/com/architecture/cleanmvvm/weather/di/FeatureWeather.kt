package com.architecture.cleanmvvm.weather.di

import android.util.Log
import com.architecture.business.weather.repository.WeatherRepository
import com.architecture.business.weather.usecase.WeatherUseCase
import com.architecture.business.weather.usecase.WeatherUseCaseImpl
import com.architecture.cleanmvvm.core.configuration.EnvConfiguration
import com.architecture.cleanmvvm.weather.viewmodel.WeatherViewModel
import com.architecture.repository.core.mapper.BaseExceptionMapperImpl
import com.architecture.repository.core.mapper.ExceptionMapper
import com.architecture.repository.weather.repository.WeatherRemoteImpl
import com.architecture.repository.weather.service.WeatherService
import com.architecture.repository.weather.service.WeatherServiceImpl
import com.jetbrains.handson.mpp.mobile.com.architecture.repository.core.log.Logger
import com.jetbrains.handson.mpp.mobile.com.architecture.repository.core.service.PlatformService.httpClientEngine
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureWeather = module {

    factory {
        val config: EnvConfiguration = get()
        WeatherServiceImpl(config.getEnvironmentUrl(), httpClientEngine) as WeatherService
    }

    factory {
        BaseExceptionMapperImpl() as ExceptionMapper
    }

    factory{
        object :Logger{
            override fun log(error: Throwable) {
                Log.v("vhgiang", "error:" + error?.printStackTrace())
            }

        } as Logger
    }

    factory<WeatherRepository> {
        WeatherRemoteImpl(
            get(),
            get(),
            get()
        )
    }

    factory<WeatherUseCase> {
        WeatherUseCaseImpl(get())
    }

    viewModel { WeatherViewModel(get(), get()) }
}