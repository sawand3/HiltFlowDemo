package com.hiltflowdemoapp.business.data.util

import com.hiltflowdemoapp.business.data.network.Resource
import com.hiltflowdemoapp.business.data.network.Status
import com.hiltflowdemoapp.business.data.util.GenericErrors.ERROR_UNKNOWN
import com.hiltflowdemoapp.business.data.util.NetworkErrors.NETWORK_ERROR
import com.hiltflowdemoapp.business.data.util.NetworkErrors.NETWORK_ERROR_TIMEOUT
import com.hiltflowdemoapp.framwork.datasource.network.model.ApiResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withContext
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

/**
 * Created by Manjinder Singh on 05,January,2021
 */

suspend fun <T : Any> apiRequest(
    dispatcher: CoroutineDispatcher,
    call: suspend () -> Response<ApiResponse<T>>
): Resource<T>? {

    return withContext(dispatcher) {
        createResource(call)
    }


}

suspend fun <T : Any> createResource(call: suspend () -> Response<ApiResponse<T>>): Resource<T>? {

    var resource: Resource<T>? = null

    try {

        val response = call.invoke()
        if (response.isSuccessful) {
            response.body()?.let {
                resource = if (response.body()?.success==true)
                    Resource.Success(it.data, it.msg, Status.SUCCESS)
                else
                    Resource.Error(message = it.msg.toString(), status = Status.ERROR)
            }
        } else {
            val error = response.errorBody()?.string()

            val message = StringBuilder()
            error?.let {
                resource = try {
                    message.append(JSONObject(it).getString("error"))
                    if (response.code() == 401)
                        Resource.Error(
                            message = message.toString(),
                            status = Status.UNAUTHROZIED
                        )
                    else
                        Resource.Error(message = message.toString(), status = Status.ERROR)
                } catch (e: JSONException) {

                    message.append(error)
                    if (response.code() == 401) {
                        Resource.Error(
                            message = message.toString(),
                            status = Status.UNAUTHROZIED
                        )
                    } else
                        Resource.Error(response.message(), Status.ERROR)

                }
            }
        }

    } catch (throwable: Throwable) {
        throwable.printStackTrace()
        resource = when (throwable) {
            is TimeoutCancellationException -> {
                Resource.Error(NETWORK_ERROR_TIMEOUT, Status.ERROR)
            }
            is IOException -> {
                Resource.NetworkError(NETWORK_ERROR, Status.ERROR)
            }
            is HttpException -> {
                val errorResponse = convertErrorBody(throwable)
                Resource.Error(errorResponse ?: "Something went wrong", Status.ERROR)
            }
            else -> {
                //   cLog(NETWORK_ERROR_UNKNOWN)
                Resource.Error("Something went wrong", Status.ERROR)
            }
        }
    }
    return resource

}


private fun convertErrorBody(throwable: HttpException): String? {
    return try {
        throwable.response()?.errorBody()?.string()
    } catch (exception: Exception) {
        ERROR_UNKNOWN
    }
}