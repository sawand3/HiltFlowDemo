package com.hiltflowdemoapp.framwork.datasource.network.retrofit

import com.hiltflowdemoapp.framwork.datasource.network.model.ApiResponse
import com.hiltflowdemoapp.framwork.datasource.network.model.LoginResponse
import retrofit2.Response

/**
 * Created by Manjinder Singh on 05,January,2021
 */
interface AppApiServices {
    suspend fun login(param:HashMap<String,String>): Response<ApiResponse<LoginResponse>>
}