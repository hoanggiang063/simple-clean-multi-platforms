package com.architecture.cleanmvvm.core.di

import com.architecture.cleanmvvm.core.configuration.EnvConfiguration
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val DATABASE = "DATABASE"
private const val DEBUG_HTTP = "DebugHttp"
private const val RELEASE_HTTP = "ReleaseHttp"

val repositoryModule = module {

    single {
        val config: EnvConfiguration = get()
        Retrofit.Builder()
            .baseUrl(config.getEnvironmentUrl())
            .client(
                get()
            )
            .addConverterFactory(MoshiConverterFactory.create())
            .build() as Retrofit
    }

}