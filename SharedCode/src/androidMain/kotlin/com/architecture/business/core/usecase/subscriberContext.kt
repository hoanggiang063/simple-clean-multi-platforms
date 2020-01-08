package com.jetbrains.handson.mpp.mobile.com.architecture.business.core.usecase

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Dispatchers

actual val subscriberContext: CoroutineContext
    get() = Dispatchers.IO
actual val observerContext: CoroutineContext
    get() = Dispatchers.Main