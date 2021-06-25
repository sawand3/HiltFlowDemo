package com.hiltflowdemoapp.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.hiltflowdemoapp.framwork.presentation.module.LoginFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

/**
 * Created by Manjinder Singh on 05,January,2021
 */

@ExperimentalCoroutinesApi
class MainFragmentFactory
@Inject
constructor(
    private val someString: String
): FragmentFactory(){

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {

            LoginFragment::class.java.name -> {
                val fragment = LoginFragment()
                fragment
            }
            else -> super.instantiate(classLoader, className)
        }
    }
}