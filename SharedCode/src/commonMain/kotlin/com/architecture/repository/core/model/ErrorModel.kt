package com.architecture.repository.core.model


class ErrorModel {
    var httpCode = 0

   // @SerializedName("errorCode")
    var errorCode: String? = null

    //@SerializedName("errorMessage")
    var errorMessage: String? = null
}