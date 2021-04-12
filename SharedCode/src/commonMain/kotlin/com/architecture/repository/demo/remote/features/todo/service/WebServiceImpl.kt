package com.jetbrains.handson.mpp.mobile.com.architecture.repository.demo.remote.features.todo.service

import com.architecture.repository.demo.model.TodoModel
import com.architecture.repository.demo.service.Webservice
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

@Suppress("EXPERIMENTAL_API_USAGE")
class WebServiceImpl(clientEngine: HttpClientEngine) : Webservice {
    val BASE_URL = "jsonplaceholder.typicode.com"

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
        return Json.decodeFromString(TodoModel.serializer(),jsonBody.readText())
    }
}