package com.hiltflowdemoapp.framwork.presentation.navHost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hiltflowdemoapp.R
import com.hiltflowdemoapp.di.MainFragmentFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}