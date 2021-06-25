package com.hiltflowdemoapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hiltflowdemoapp.business.data.network.AuthenticationInterceptor
import com.hiltflowdemoapp.framwork.datasource.network.retrofit.ApiService
import com.hiltflowdemoapp.framwork.datasource.network.retrofit.AppApiServiceImpl
import com.hiltflowdemoapp.framwork.datasource.network.retrofit.AppApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Manjinder Singh on 05,January,2021
 */


@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }


    @Singleton
    @Provides
    fun providersLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun providedAuthenticationInterceptor(): AuthenticationInterceptor {
        return AuthenticationInterceptor(
            userName = "",
            password = ""
        )
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authenticationInterceptor: AuthenticationInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authenticationInterceptor)
            .readTimeout(240, TimeUnit.SECONDS)
            .connectTimeout(240, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("http://vienhealth.alcax.com:5000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)

    }


    @Singleton
    @Provides
    fun provideBlogService(retrofit: Retrofit.Builder): ApiService {
        return retrofit
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofitService(
        blogRetrofit: ApiService
    ): AppApiServices {
        return AppApiServiceImpl(blogRetrofit)
    }

}