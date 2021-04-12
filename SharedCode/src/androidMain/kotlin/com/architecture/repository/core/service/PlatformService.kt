package com.jetbrains.handson.mpp.mobile.com.architecture.repository.core.service

import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*
import okhttp3.logging.HttpLoggingInterceptor

actual object PlatformService {
    actual val httpClientEngine: HttpClientEngine by lazy {
        OkHttp.create {
            val networkInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            addNetworkInterceptor(networkInterceptor)
        }
    }
}