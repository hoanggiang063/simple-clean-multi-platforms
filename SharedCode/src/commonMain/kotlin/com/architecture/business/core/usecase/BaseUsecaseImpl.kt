package com.architecture.business.core.usecase

import com.architecture.business.core.callback.BasePresentCallBack
import com.architecture.business.core.repository.BaseRepository
import com.jetbrains.handson.mpp.mobile.com.architecture.business.core.usecase.observerContext
import com.jetbrains.handson.mpp.mobile.com.architecture.business.core.usecase.subscriberContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseUsecaseImpl<Param, Result, CallBack : BasePresentCallBack<Result>>
    (val bankRepository: BaseRepository<Param, Result>) : BaseUseCase<Param, Result, CallBack> {
    var subscriberContextUseCase = subscriberContext
    var observerContextUseCase = observerContext

    override fun buildUseCase(param: Param): BaseUsecaseImpl<Param, Result, CallBack> {
        bankRepository.setParam(param)
        return this
    }

    override fun invoke(callback: CallBack): Job {
        return CoroutineScope(subscriberContextUseCase).launch {
            // implement thread to call
            var data: Result?

            try {
                data = bankRepository()
                withContext(observerContextUseCase) {
                    handleSuccess(data, callback)
                }
            } catch (error: Throwable) {
                withContext(observerContextUseCase) {
                    handleFail(error, callback)
                }

            }

        }
    }

    private fun handleSuccess(result: Result?, callback: CallBack) {
        callback.onSuccess(result)
    }

    private fun handleFail(error: Throwable, callback: CallBack) {

        if (!hanldeExceptionByChild(error, callback)) {
            handleExceptionByGeneric(error, callback)
        } else {
            callback.onFail(error)
        }
    }

    abstract fun hanldeExceptionByChild(error: Throwable, callback: CallBack): Boolean

    open fun handleExceptionByGeneric(error: Throwable, callback: CallBack) {
        callback.onFail(error)
    }

     override fun execute(callback: CallBack): Job {
         return CoroutineScope(subscriberContext).launch {
             // implement thread to call
             var data: Result?

             try {
                 data = bankRepository()
                 withContext(observerContext) {
                     handleSuccess(data, callback)
                 }
             } catch (error: Throwable) {
                 withContext(observerContext) {
                     handleFail(error, callback)
                 }

             }

         }
     }
 }



