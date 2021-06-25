package com.hiltflowdemoapp.base.presentation.extensions

import android.os.Build
import android.os.SystemClock
import android.provider.Settings
import android.util.Base64
import android.view.View
import com.hiltflowdemoapp.base.delegate.MyApplication
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Manjinder Singh on 05,January,2021
 */


fun View.setOnDebouncedClickListener(action: () -> Unit) {
    val actionDebouncer = ActionDebouncer(action)

    // This is the only place in the project where we should actually use setOnClickListener
    setOnClickListener {
        actionDebouncer.notifyAction()
    }
}

fun View.removeOnDebouncedClickListener() {
    setOnClickListener(null)
    isClickable = false
}

private class ActionDebouncer(private val action: () -> Unit) {

    companion object {
        const val DEBOUNCE_INTERVAL_MILLISECONDS = 600L
    }

    private var lastActionTime = 0L

    fun notifyAction() {
        val now = SystemClock.elapsedRealtime()

        val millisecondsPassed = now - lastActionTime
        val actionAllowed = millisecondsPassed > DEBOUNCE_INTERVAL_MILLISECONDS
        lastActionTime = now

        if (actionAllowed) {
            action.invoke()
        }
    }
}

fun getDeviceInfo(): String {
    var menufacture = Build.MANUFACTURER
    var name = Build.MODEL
    return "$menufacture,$name"
}


fun deviceId(): String {
    return Settings.Secure.getString(
        MyApplication.application.contentResolver,
        Settings.Secure.ANDROID_ID
    )
}
// Has line break
fun String.base64(): String? {
    return Base64.encodeToString(this.toByteArray(), Base64.DEFAULT).replace("\n", "")
}

fun currentDeviceZone(): String {
    val sdf = SimpleDateFormat("ZZZZZ")
    val currentDate = sdf.format(Date())
    println(" C DATE is  " + currentDate)
    return currentDate
}