package com.example.composeex.core.di

import com.example.data.api.apiService
import com.example.data.repository.BoxOfficeSearchRepositoryImpl
import com.example.domain.repository.BoxOfficeSearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideBoxOfficeListRepository(
        apiService: apiService
    ) : BoxOfficeSearchRepository {
        return BoxOfficeSearchRepositoryImpl(apiService)
    }
}