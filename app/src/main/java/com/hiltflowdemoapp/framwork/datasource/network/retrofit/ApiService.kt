package com.hiltflowdemoapp.framwork.datasource.network.retrofit

import com.hiltflowdemoapp.framwork.datasource.network.model.ApiResponse
import com.hiltflowdemoapp.framwork.datasource.network.model.LoginResponse
import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by Manjinder Singh on 05,January,2021
 */


interface ApiService {
    @FormUrlEncoded
    @POST("api/patient/login")
    suspend fun loginUser(
        @FieldMap params: HashMap<String, String>
    ): Response<ApiResponse<LoginResponse>>
}