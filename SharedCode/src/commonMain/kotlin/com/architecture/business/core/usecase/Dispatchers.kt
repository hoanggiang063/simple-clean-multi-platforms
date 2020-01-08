package com.jetbrains.handson.mpp.mobile.com.architecture.business.core.usecase

import kotlin.coroutines.CoroutineContext

expect val subscriberContext: CoroutineContext
expect val observerContext: CoroutineContext