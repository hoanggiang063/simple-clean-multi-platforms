package com.architecture.business.demo.callback

import com.architecture.business.core.callback.BasePresentCallBack
import com.architecture.business.demo.info.ToDoInfo

interface ToDoCallBack : BasePresentCallBack<ToDoInfo> {
    override fun onSuccess(expectedResult: ToDoInfo?)
    override fun onFail(throwable: Throwable)
}