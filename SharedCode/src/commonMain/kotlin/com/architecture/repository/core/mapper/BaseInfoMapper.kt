package com.architecture.repository.core.mapper

interface BaseInfoMapper<In, Out> {
    fun transform(input: In): Out
}