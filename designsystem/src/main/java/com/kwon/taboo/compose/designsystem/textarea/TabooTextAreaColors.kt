package com.kwon.taboo.compose.designsystem.textarea

import androidx.compose.ui.graphics.Color

data class TabooTextAreaColors(
    val textColor: Color,
    val disabledTextColor: Color,
    val placeHolderColor: Color,
    val helpTextColor: Color,
    val backgroundColor: Color,
    val disabledBackgroundColor: Color,
    val focusedBackgroundColor: Color
) {
    fun textColor(enabled: Boolean) : Color {
        return if (enabled) textColor else disabledTextColor
    }

    fun placeHolderColor() : Color {
        return placeHolderColor
    }

    fun helpTextColor() : Color {
        return helpTextColor
    }

    fun backgroundColor(enabled: Boolean, focused: Boolean) : Color {
        return if (enabled) {
            if (focused) {
                focusedBackgroundColor
            } else {
                backgroundColor
            }
        } else {
            disabledBackgroundColor
        }
    }
}