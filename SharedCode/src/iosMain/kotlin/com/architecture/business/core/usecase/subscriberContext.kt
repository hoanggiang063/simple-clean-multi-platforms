package com.jetbrains.handson.mpp.mobile.com.architecture.business.core.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import platform.darwin.DISPATCH_QUEUE_PRIORITY_DEFAULT
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_global_queue
import platform.darwin.dispatch_get_main_queue
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.freeze

actual val subscriberContext: CoroutineContext
    get() = IosMainDispatcher
actual val observerContext: CoroutineContext
    get() = IosMainDispatcher

private object IosMainDispatcher : CoroutineDispatcher() {

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        dispatch_async(dispatch_get_main_queue()) { block.run() }
    }
}

private object IosDefaultDispatcher : CoroutineDispatcher() {

    @ExperimentalUnsignedTypes
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0)) {
            block.freeze().run()
        }
    }
}

//To change initializer of created properties use File | Settings | File Templates.