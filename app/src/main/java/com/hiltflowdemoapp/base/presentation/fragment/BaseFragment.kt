package com.hiltflowdemoapp.base.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

/**
 * Created by Manjinder Singh on 05,January,2021
 */

abstract class BaseFragment : Fragment() {
    @LayoutRes
    abstract fun getFragmentLayoutResId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getFragmentLayoutResId(), container, false)
    }
}