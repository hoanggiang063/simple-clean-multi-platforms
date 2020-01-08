package com.architecture.business.core.usecase

import com.architecture.business.core.callback.BasePresentCallBack
import kotlinx.coroutines.Job

interface BaseUseCase<Param, Result, CallBack : BasePresentCallBack<Result>> {

    fun buildUseCase(param: Param): BaseUsecaseImpl<Param, Result, CallBack>

    operator fun invoke(callback: CallBack): Job

    fun execute(callback: CallBack): Job
}