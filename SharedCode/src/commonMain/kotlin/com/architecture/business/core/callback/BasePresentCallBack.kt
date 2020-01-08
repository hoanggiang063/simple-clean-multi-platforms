package com.architecture.business.core.callback

interface BasePresentCallBack<Result> {
    fun onSuccess(expectedResult: Result?)
    fun onFail(throwable: Throwable)
}