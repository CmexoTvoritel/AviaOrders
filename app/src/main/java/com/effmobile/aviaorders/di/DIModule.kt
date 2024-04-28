package com.effmobile.aviaorders.di

import com.effmobile.aviaorders.data.RetrofitClient
import com.effmobile.aviaorders.data.alltickets.repository.AllTicketsRepositoryImpl
import com.effmobile.aviaorders.data.main.repository.MainRepositoryImpl
import com.effmobile.aviaorders.data.search.repository.SearchRepositoryImpl
import com.effmobile.aviaorders.domain.repository.alltickets.AllTicketsRepository
import com.effmobile.aviaorders.domain.repository.main.MainRepository
import com.effmobile.aviaorders.domain.repository.search.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitClient(): RetrofitClient {
        return RetrofitClient()
    }

    @Singleton
    @Provides
    fun provideMainApiService(
        retrofitClient: RetrofitClient
    ) = retrofitClient.provideMainApiService()

    @Singleton
    @Provides
    fun provideSearchApiService(
        retrofitClient: RetrofitClient
    ) = retrofitClient.provideSearchApiService()

    @Singleton
    @Provides
    fun provideAllTicketsApiService(
        retrofitClient: RetrofitClient
    ) = retrofitClient.provideAllTicketsApiService()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {

    @Binds
    abstract fun bindMainRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository

    @Binds
    abstract fun bindSearchRepository(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository

    @Binds
    abstract fun bindAllTicketsRepository(allTicketsRepositoryImpl: AllTicketsRepositoryImpl): AllTicketsRepository
}