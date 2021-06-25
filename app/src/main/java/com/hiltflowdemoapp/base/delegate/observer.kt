package com.hiltflowdemoapp.base.delegate

import kotlin.properties.ObservableProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by Manjinder Singh on 05,January,2021
 */

inline fun <T> observer(
    initialValue: T,
    crossinline onChange: (newValue: T) -> Unit
):
        ReadWriteProperty<Any?, T> =
    object : ObservableProperty<T>(initialValue) {
        override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) =
            onChange(newValue)
    }