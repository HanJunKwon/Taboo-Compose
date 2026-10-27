package com.kwon.taboo.compose.designsystem.toast

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

@Stable
class TabooToastState {
    var isVisible by mutableStateOf(false)
        private set

    var message by mutableStateOf("")
        private set

    suspend fun show(newMessage: String, duration: Duration = 3_000.milliseconds) {
        if (isVisible) {
            isVisible = false
            delay(100.milliseconds)
        }

        message = newMessage

        isVisible = true

        delay(duration)

        isVisible = false
    }
}