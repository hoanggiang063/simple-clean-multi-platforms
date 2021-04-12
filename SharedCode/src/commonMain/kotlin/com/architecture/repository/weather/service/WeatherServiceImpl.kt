package com.architecture.repository.weather.service

import com.architecture.repository.weather.model.WeatherModel
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

@Suppress("EXPERIMENTAL_API_USAGE")
class WeatherServiceImpl(val baseURL: String, clientEngine: HttpClientEngine) : WeatherService {

    private val client = HttpClient(clientEngine) {
        install(JsonFeature) {
            serializer = defaultSerializer()
        }
    }

    override suspend fun getWeather(
        city: String,
        numberOfDate: String,
        appId: String,
        unit: String
    ): WeatherModel {
        val response = client.get<HttpStatement> {
            url {
                protocol = URLProtocol.HTTPS
                host = baseURL
                encodedPath = "/data/2.5/forecast/daily"
                parameters.append("q", city)
                parameters.append("cnt", numberOfDate)
                parameters.append("appid", appId)
                parameters.append("units", unit)
            }
        }
        val jsonBody = response.execute()
        val format = Json { ignoreUnknownKeys = true ; isLenient = true }
        return format.decodeFromString<WeatherModel>(WeatherModel.serializer(), jsonBody.readText())
    }
}