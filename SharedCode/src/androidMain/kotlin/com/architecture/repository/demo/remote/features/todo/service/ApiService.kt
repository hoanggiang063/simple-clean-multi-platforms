package com.jetbrains.handson.mpp.mobile.com.architecture.repository.demo.remote.features.todo.service

import com.architecture.repository.demo.model.TodoModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {
    @GET("/todos/{id}")
    fun getTodo(@Path(value = "id") todoId: Int): Call<TodoModel>
}
