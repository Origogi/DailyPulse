package com.origogi.dailypulse

import android.content.res.Resources
import android.os.Build
import android.util.Log
import kotlin.math.round

actual object Platform {
    actual val osName: String = "Android"
    actual val osVersion: String = Build.VERSION.RELEASE ?: "Unknown"
    actual val deviceModel: String = "${Build.MANUFACTURER} ${Build.MODEL}"
    actual val density: Int = round(Resources.getSystem().displayMetrics.density).toInt()

    actual fun logSystemInfo() {
        Log.d(
            "PlatformInfo",
            "OS: $osName $osVersion, Device: $deviceModel, Density: ${density}dpi"
        )
    }
}
