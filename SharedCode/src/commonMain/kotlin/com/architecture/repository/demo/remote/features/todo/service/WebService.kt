package com.architecture.repository.demo.service

import com.architecture.repository.demo.model.TodoModel

interface Webservice {
    suspend fun getTodo(todoId: Int): TodoModel
}