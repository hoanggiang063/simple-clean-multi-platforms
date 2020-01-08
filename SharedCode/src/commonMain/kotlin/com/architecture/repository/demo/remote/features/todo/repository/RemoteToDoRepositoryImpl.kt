package com.architecture.repository.demo.repository

import com.architecture.business.demo.info.ToDoInfo
import com.architecture.business.demo.repository.ToDoRepository
import com.architecture.repository.core.mapper.ExceptionMapper
import com.architecture.repository.demo.mapper.RemoteTodoMapper
import com.architecture.repository.demo.service.Webservice

class RemoteToDoRepositoryImpl(
    var service: Webservice,
    var exception: ExceptionMapper,
    var log: Log
) :
    ToDoRepository {

    var myParam: Int = 0;
    override fun setParam(param: Int) {
        this.myParam = param;
    }

    override fun invoke(): ToDoInfo {
        try {
            return RemoteTodoMapper().transform(service.getTodo(myParam))
        } catch (error: Throwable) {
            log.log(error)
            throw exception.transform(error)
        }
    }
}

interface Log {
    fun log(error: Throwable)
}