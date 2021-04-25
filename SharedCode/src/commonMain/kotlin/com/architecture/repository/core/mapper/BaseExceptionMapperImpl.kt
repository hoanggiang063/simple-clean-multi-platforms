package com.jetbrains.handson.mpp.mobile.com.architecture.repository.core.mapper

import com.architecture.business.core.exception.BaseException
import com.architecture.business.core.exception.TechnicalException
import com.architecture.business.core.exception.UnknownException
import com.architecture.repository.core.mapper.ExceptionMapper
import io.ktor.utils.io.errors.*


open class BaseExceptionMapperImpl : ExceptionMapper {

    override fun transform(input: Throwable?): BaseException {
        return when (input) {
            null -> UnknownException()

            is IOException -> TechnicalException()

            else -> UnknownException()
            }
    }

}