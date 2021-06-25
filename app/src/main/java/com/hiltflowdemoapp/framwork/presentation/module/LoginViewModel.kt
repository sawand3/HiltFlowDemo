package com.hiltflowdemoapp.framwork.presentation.module

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.hiltflowdemoapp.base.presentation.viewmodel.BaseAction
import com.hiltflowdemoapp.base.presentation.viewmodel.BaseViewModel
import com.hiltflowdemoapp.base.presentation.viewmodel.BaseViewState
import com.hiltflowdemoapp.business.data.network.Resource
import com.hiltflowdemoapp.business.interactors.UserUseCase
import com.hiltflowdemoapp.framwork.datasource.network.model.LoginResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * Created by Manjinder Singh on 05,January,2021
 */


@ExperimentalCoroutinesApi
internal class LoginViewModel
@ViewModelInject
constructor(
    private val userUseCase: UserUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel<LoginViewModel.ViewState, LoginViewModel.Action>(
    ViewState()
) {


    fun login(param:HashMap<String,String>){
        viewModelScope.launch {
            userUseCase.login(param).onEach {dataState->
                when(dataState){
                    is Resource.Success ->
                        sendAction(Action.LoginApiData(data = dataState.data))


                    is Resource.Error ->
                        sendAction(
                            Action.Error(
                                error = dataState.msg
                            )
                        )


                    is Resource.NetworkError -> sendAction(
                        Action.Error(
                            error = dataState.msg
                        )
                    )
                }
            }.launchIn(viewModelScope)
        }
    }


    internal data class ViewState(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val error: Any? = null,
        val loginData: LoginResponse? = null

    ) : BaseViewState

    internal sealed class Action : BaseAction {
        class LoginApiData(val data: LoginResponse?) : Action()
        class Error(val error: Any?) : Action()
    }

    override fun onReduceState(viewAction: Action) = when (viewAction) {
        is Action.Error -> state.copy(
            isLoading = false,
            isError = true,
            error = viewAction.error,
            loginData = null
        )

        is Action.LoginApiData -> state.copy(
            isLoading = false,
            isError = false,
            error = null,
            loginData = viewAction.data
        )
    }
}