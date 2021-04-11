package com.architecture.repository.weather.service

import com.architecture.repository.weather.model.WeatherModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.defaultSerializer
import io.ktor.client.request.get
import io.ktor.client.statement.HttpStatement
import io.ktor.client.statement.readText
import io.ktor.http.URLProtocol
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
            }
        }
        val jsonBody = response.execute()
        return Json.parse(WeatherModel.serializer(), jsonBody.readText())
    }
}