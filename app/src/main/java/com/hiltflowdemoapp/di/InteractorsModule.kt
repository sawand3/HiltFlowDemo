package com.hiltflowdemoapp.di

import com.hiltflowdemoapp.business.interactors.UserUseCase
import com.hiltflowdemoapp.framwork.datasource.network.retrofit.AppApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * Created by Manjinder Singh on 05,January,2021
 */


@Module
@InstallIn(ApplicationComponent::class)
object InteractorsModule {

    @Singleton
    @Provides
    fun provideGetUserCase(
        networkDataSource: AppApiServices
    ): UserUseCase{
        return UserUseCase( networkDataSource)
    }
}