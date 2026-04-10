package com.kwon.taboo.compose.designsystem.button

import androidx.compose.ui.graphics.Color

data class TabooTextButtonColors(
    val textColor: Color,
    val disabledColor: Color,
    val iconColor : Color,
    val disabledIconColor: Color,
    val backgroundColor: Color,
    val pressedBackgroundColor: Color,
    val disabledBackgroundColor: Color
) {
    fun textColor(enabled: Boolean): Color {
        return if (enabled) textColor else disabledColor
    }

    fun iconColor(enabled: Boolean): Color {
        return if (enabled) iconColor else disabledIconColor
    }

    fun backgroundColor(enabled: Boolean, isPressed: Boolean): Color {
        return if (enabled) {
            if (isPressed) {
                pressedBackgroundColor
            } else {
                backgroundColor
            }
        } else {
            disabledBackgroundColor
        }
    }
}
