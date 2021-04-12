package com.jetbrains.handson.mpp.mobile.com.architecture.repository.core.service

import io.ktor.client.engine.*

expect object PlatformService {

    val httpClientEngine: HttpClientEngine
}