package com.kwon.taboo.compose.designsystem.popup

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

@Stable
class TabooPopupMenuState(
    var alignment: Alignment = Alignment.TopStart
) {
    var isVisible by mutableStateOf(false)
        private set

    fun show() {
        isVisible = true
    }

    fun dismiss() {
        isVisible = false
    }
}