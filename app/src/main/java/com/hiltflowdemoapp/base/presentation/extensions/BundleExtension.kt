package com.hiltflowdemoapp.base.presentation.extensions

import android.os.Bundle
import androidx.core.os.bundleOf

/**
 * Created by Manjinder Singh on 05,January,2021
 */
fun Bundle.putAny(key: String, value: Any?) {
    putAll(bundleOf(key to value))
}