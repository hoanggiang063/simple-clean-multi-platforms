package com.jetbrains.handson.mpp.mobile.com.architecture.repository.core.service

import io.ktor.client.engine.HttpClientEngine

expect object PlatformService {

    val httpClientEngine: HttpClientEngine
}