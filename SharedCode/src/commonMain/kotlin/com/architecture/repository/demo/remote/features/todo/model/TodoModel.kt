package com.architecture.repository.demo.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TodoModel(

    @SerialName("userId")
    val userId: Int = 0,
    @SerialName("id")
    val id: Int = 0,
    @SerialName("title")
    val title: String = "",
    @SerialName("completed")
    val completed: Boolean = false
)