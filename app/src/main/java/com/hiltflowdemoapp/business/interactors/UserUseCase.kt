package com.hiltflowdemoapp.business.interactors

import com.hiltflowdemoapp.business.data.network.Resource
import com.hiltflowdemoapp.business.data.util.apiRequest
import com.hiltflowdemoapp.framwork.datasource.network.model.LoginResponse
import com.hiltflowdemoapp.framwork.datasource.network.retrofit.AppApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Manjinder Singh on 05,January,2021
 */

class UserUseCase
constructor(
    private val networkDataSource: AppApiServices
) {
    suspend fun login(param:HashMap<String,String>):Flow<Resource<LoginResponse>?> = flow {
        val data= apiRequest(Dispatchers.IO){
            networkDataSource.login(param)
        }
        emit(data)
    }
}