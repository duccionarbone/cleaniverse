package com.duccionarbone.cleanarchitectured.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @Provides
    @Singleton
    fun providesDispatcherIO(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    @DispatcherMain
    fun providesDispatcherMain(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @Singleton
    @DispatcherDefault
    fun providesDispatcherDefault(): CoroutineDispatcher = Dispatchers.Default
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DispatcherIO

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DispatcherMain

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DispatcherDefault