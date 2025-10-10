package com.origogi.dailypulse

import kotlin.time.Clock
import kotlin.time.Duration.Companion.hours
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
fun String.daysAgo(): String {
    val now = Clock.System.now()
    val publishedAt = Instant.parse(this)
    val duration = now - publishedAt

    return when {
        duration < 24.hours -> "Today"
        duration < 48.hours -> "Yesterday"
        else -> "${duration.inWholeDays} days ago"
    }
}