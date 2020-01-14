package com.jetbrains.handson.mpp.mobile.com.architecture.repository.core.service

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.ios.Ios

@ThreadLocal
actual object PlatformService {

    actual val httpClientEngine: HttpClientEngine by lazy { Ios.create() }
}