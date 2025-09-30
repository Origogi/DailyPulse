package com.origogi.dailypulse

import platform.Foundation.NSLog
import platform.UIKit.UIDevice
import platform.UIKit.UIScreen

actual object Platform {
    actual val osName: String = UIDevice.currentDevice.systemName()
    actual val osVersion: String = UIDevice.currentDevice.systemVersion()
    actual val deviceModel: String = UIDevice.currentDevice.model
    actual val density: Int = UIScreen.mainScreen.scale.toInt()

    actual fun logSystemInfo() {
        NSLog("PlatformInfo OS: $osName $osVersion, Device: $deviceModel, Density: $density")
    }
}