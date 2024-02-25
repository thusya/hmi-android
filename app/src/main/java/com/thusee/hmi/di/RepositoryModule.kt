package com.thusee.hmi.di

import com.thusee.hmi.data.local.CounterRepositoryImpl
import com.thusee.hmi.data.repository.CounterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCounterRepository(
        repositoryImpl: CounterRepositoryImpl
    ): CounterRepository
}