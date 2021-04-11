package com.architecture.repository.weather.repository

import com.architecture.business.weather.info.WeatherInfo
import com.architecture.business.weather.info.WeatherItemInfo
import com.architecture.business.weather.repository.WeatherRepository
import com.architecture.business.weather.usecase.WeatherRequest
import com.architecture.repository.core.mapper.ExceptionMapper
import com.architecture.repository.weather.model.ForeCast
import com.architecture.repository.weather.model.Weather
import com.architecture.repository.weather.model.WeatherModel
import com.architecture.repository.weather.service.WeatherService
import com.jetbrains.handson.mpp.mobile.com.architecture.repository.core.log.Logger

class WeatherRemoteImpl(
    var service: WeatherService,
    var exception: ExceptionMapper,
    var log: Logger
) : WeatherRepository {

    lateinit var request: WeatherRequest

    override fun setParam(param: WeatherRequest) {
        request = param
    }

    override suspend fun invoke(): WeatherInfo {
        try {
            return RemoteMapper().transform(
                service.getWeather(
                    request.city,
                    request.numberDays.toString(),
                    request.appId,
                    request.unit
                )
            )
        } catch (error: Throwable) {
            log.log(error)
            throw exception.transform(error)
        }
    }
}

class RemoteMapper {
    fun transform(input: WeatherModel): WeatherInfo {
        val weatherInfo = WeatherInfo()
        weatherInfo.cityName = input.city.name
        weatherInfo.county = input.city.country
        weatherInfo.id = input.city.id
        weatherInfo.lat = input.city.coord.lat
        weatherInfo.long = input.city.coord.lon
        weatherInfo.timeZone = input.city.timeZone
        input.list.let {
            weatherInfo.foreCastItems = transformItem(input.list)
        }
        return weatherInfo
    }

    private fun transformItem(list: List<ForeCast>): List<WeatherItemInfo> {
        val itemInfo = mutableListOf<WeatherItemInfo>()
        list.forEach { inItem ->
            val outItem = WeatherItemInfo()
            outItem.pressure = inItem.pressure
            outItem.temperature = inItem.temp.eve
            outItem.description = getItemDescription(inItem.weather)
            outItem.humanity = inItem.humidity
            outItem.date = inItem.dt
            itemInfo.add(outItem)
        }
        return itemInfo
    }

    private fun getItemDescription(weather: List<Weather>): String {
        var totalDescription = ""
        weather.forEach { item ->
            totalDescription += item.description
        }
        return totalDescription
    }

}
