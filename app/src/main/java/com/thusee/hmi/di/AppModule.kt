package com.thusee.hmi.di

import com.thusee.hmi.data.local.CounterRepositoryImpl
import com.thusee.hmi.data.repository.CounterRepository
import com.thusee.hmi.domain.usecase.CounterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCounterRepository(repository: CounterRepositoryImpl): CounterRepository {
        return repository
    }

    @Singleton
    @Provides
    fun provideCounterUseCase(repository: CounterRepository): CounterUseCase {
        return CounterUseCase(repository)
    }

}