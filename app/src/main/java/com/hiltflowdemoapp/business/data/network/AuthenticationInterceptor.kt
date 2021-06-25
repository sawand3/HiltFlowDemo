package com.hiltflowdemoapp.business.data.network

import com.hiltflowdemoapp.base.presentation.extensions.currentDeviceZone
import com.hiltflowdemoapp.base.presentation.extensions.deviceId
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Created by Manjinder Singh on 05,January,2021
 */

class AuthenticationInterceptor(private val userName: String, private val password: String) :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = chain.request().let {

//        val authKey = PrefsManager.get().getString(Constants.ACCESS_TOKEN, null)
        val newRequest: Request?

//        newRequest = if (authKey != null) {
//            it.newBuilder()
//                .addHeader("token", authKey)
//                .build()
//        } else {
        newRequest = it.newBuilder()
            .addHeader("deviceToken", deviceId())
            .addHeader("tz", currentDeviceZone())
            .build()
//        }

        chain.proceed(newRequest)
    }
}