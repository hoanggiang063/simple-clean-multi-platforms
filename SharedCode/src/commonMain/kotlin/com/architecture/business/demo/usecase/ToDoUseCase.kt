package com.architecture.business.demo.usecase

import com.architecture.business.core.usecase.BaseUseCase
import com.architecture.business.demo.callback.ToDoCallBack
import com.architecture.business.demo.info.ToDoInfo

interface ToDoUseCase : BaseUseCase<Int, ToDoInfo, ToDoCallBack> {
}