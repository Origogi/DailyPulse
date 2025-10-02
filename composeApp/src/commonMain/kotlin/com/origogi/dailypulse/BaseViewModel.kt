package com.origogi.dailypulse

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope

expect open class BaseViewModel() : ViewModel {
    val scope : CoroutineScope
}