package com.hiltflowdemoapp.framwork.datasource.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Manjinder Singh on 05,January,2021
 */


data class ApiResponse<T>(
    val success: Boolean,
    val msg: String?=null,
    val data: T
)