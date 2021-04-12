package com.jetbrains.handson.mpp.mobile.com.architecture.business.core.usecase

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

actual val subscriberContext: CoroutineContext
    get() = Dispatchers.IO
actual val observerContext: CoroutineContext
    get() = Dispatchers.Main