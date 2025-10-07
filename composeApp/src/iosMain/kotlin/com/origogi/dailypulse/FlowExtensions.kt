package com.origogi.dailypulse

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class Closeable(private val job: Job) {
    fun close() {
        job.cancel()
    }
}

fun <T> StateFlow<T>.watch(onChange: (T) -> Unit): Closeable {
    val job = CoroutineScope(Dispatchers.Main).launch {
        collect { onChange(it) }
    }
    return Closeable(job)
}
