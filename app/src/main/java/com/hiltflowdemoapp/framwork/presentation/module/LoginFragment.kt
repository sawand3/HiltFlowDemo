package com.hiltflowdemoapp.framwork.presentation.module

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.hiltflowdemoapp.R
import com.hiltflowdemoapp.base.presentation.extensions.base64
import com.hiltflowdemoapp.base.presentation.extensions.getDeviceInfo
import com.hiltflowdemoapp.base.presentation.extensions.observe
import com.hiltflowdemoapp.base.presentation.fragment.BaseContainerFragment
import com.hiltflowdemoapp.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class LoginFragment : BaseContainerFragment<FragmentLoginBinding>() {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = getViewDataBinding()
        observe(viewModel.stateLiveData, stateObserver)
        Clicks()

    }

    private val stateObserver = Observer<LoginViewModel.ViewState> {
        Log.e("DATA",it.loginData.toString())
    }

    private fun Clicks() {
        binding.loginButton.setOnClickListener {
            var param = HashMap<String, String>()
            param["email"]="brock@mailinator.com"
            param["password"]="Root@123"
            viewModel.login(param)
        }
    }

    override val layoutResourceId: Int
        get() = R.layout.fragment_login
}