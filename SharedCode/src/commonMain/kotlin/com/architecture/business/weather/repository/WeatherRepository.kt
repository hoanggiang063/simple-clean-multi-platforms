package com.architecture.business.weather.repository

import com.architecture.business.core.repository.BaseRepository
import com.architecture.business.weather.info.WeatherInfo
import com.architecture.business.weather.usecase.WeatherRequest

interface WeatherRepository : BaseRepository<WeatherRequest, WeatherInfo>