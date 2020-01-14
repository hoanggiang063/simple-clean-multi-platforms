package com.jetbrains.handson.mpp.mobile.com.architecture.repository.demo.remote.features.todo.service

import com.architecture.repository.demo.model.TodoModel
import com.architecture.repository.demo.service.Webservice
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
class WebServiceImpl(clientEngine: HttpClientEngine) : Webservice {
    val BASE_URL = "https://jsonplaceholder.typicode.com/"

    private val client = HttpClient(clientEngine) {
        install(JsonFeature) {
            serializer = defaultSerializer()
        }
    }

    override suspend fun getTodo(todoId: Int): TodoModel {
        val response = client.get<HttpStatement> {
            url {
                protocol = URLProtocol.HTTPS
                host = BASE_URL
                encodedPath = "/todos/1"
                //parameter("sort_by", "popularity.desc")
            }
        }
        val jsonBody = response.execute()
        return Json.parse(TodoModel.serializer(), jsonBody.readText())
    }
}