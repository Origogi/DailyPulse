package com.origogi.dailypulse

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform