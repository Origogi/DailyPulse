package com.origogi.dailypulse

expect object Platform {
    val osName : String
    val osVersion : String
    val deviceModel : String
    val density : Int

    fun logSystemInfo()
}