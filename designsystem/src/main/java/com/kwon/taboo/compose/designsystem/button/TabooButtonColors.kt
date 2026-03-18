package com.kwon.taboo.compose.designsystem.button

import androidx.compose.ui.graphics.Color

class TabooButtonColors(
    val containerColor: Color,
    val contentColor: Color,
    val pressedContainerColor: Color,
    val pressedContentColor: Color,
    val disableContainerColor: Color,
    val disableContentColor: Color
) {
    fun containerColor(enabled: Boolean, isPressed: Boolean): Color {
        return if (enabled) {
            if (isPressed) pressedContainerColor else containerColor
        } else {
            disableContainerColor
        }
    }

    fun contentColor(enabled: Boolean, isPressed: Boolean): Color {
        return if (enabled) {
            if (isPressed) pressedContentColor else contentColor
        } else {
            disableContentColor
        }
    }
}