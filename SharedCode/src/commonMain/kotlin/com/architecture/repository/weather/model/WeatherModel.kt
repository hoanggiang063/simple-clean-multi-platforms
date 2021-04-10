package com.architecture.repository.weather.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherModel(
    @SerialName("city")
    var city: City,

    @SerialName("list")
    var list: List<ForeCast>
)

@Serializable
data class City(
    @SerialName("id")
    var id: String,

    @SerialName("name")
    var name: String,

    @SerialName("coord")
    var coord: CityCoord,

    @SerialName("country")
    var country: String,

    @SerialName("population")
    var population: Int = 0,

    @SerialName("timeZone")
    var timeZone: String
)

@Serializable
data class CityCoord(
    @SerialName("lon")
    var lon: Double = 0.0,

    @SerialName("lat")
    var lat: Double = 0.0
)

@Serializable
data class ForeCast(
    @SerialName("dt")
    var dt: Long = 0,

    @SerialName("sunrise")
    var sunrise: Double = 0.0,

    @SerialName("sunset")
    var sunset: Double = 0.0,

    @SerialName("temp")
    var temp: Temperature,

    @SerialName("feels_like")
    var feels_like: FeelsLike,

    @SerialName("pressure")
    var pressure: Int = 0,

    @SerialName("humidity")
    var humidity: Int = 0,

    @SerialName("weather")
    var weather: List<Weather>,

    @SerialName("speed")
    var speed: Double = 0.0,

    @SerialName("deg")
    var deg: Double = 0.0,

    @SerialName("clouds")
    var clouds: Double = 0.0,

    @SerialName("rain")
    var rain: Double = 0.0
)

@Serializable
data class Temperature(
    @SerialName("day")
    var day: Double = 0.0,

    @SerialName("min")
    var min: Double = 0.0,

    @SerialName("max")
    var max: Double = 0.0,

    @SerialName("night")
    var night: Double = 0.0,

    @SerialName("eve")
    var eve: Double = 0.0,

    @SerialName("morn")
    var morn: Double = 0.0
)

@Serializable
data class FeelsLike(
    @SerialName("day")
    var day: Double = 0.0,

    @SerialName("night")
    var night: Double = 0.0,

    @SerialName("eve")
    var eve: Double = 0.0,

    @SerialName("morn")
    var morn: Double = 0.0
)

@Serializable
data class Weather(
    @SerialName("id")
    var id: String,

    @SerialName("main")
    var main: String,

    @SerialName("description")
    var description: String,

    @SerialName("icon")
    var icon: String
)
