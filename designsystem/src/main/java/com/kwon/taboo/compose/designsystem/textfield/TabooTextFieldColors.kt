package com.kwon.taboo.compose.designsystem.textfield

import androidx.compose.ui.graphics.Color

data class TabooTextFieldColors(
    val titleColor: Color,
    val focusedTitleColor: Color,
    val disabledTitleColor: Color,
    val textColor: Color,
    val focusedTextColor: Color,
    val disabledTextColor: Color,
    val placeHolderColor: Color,
    val contentColor: Color,
    val disabledContentColor: Color,
    val lineColor: Color,
    val focusedLineColor: Color,
    val disabledLineColor: Color,
    val showPasswordColor: Color,
    val hidePasswordColor: Color
) {
    fun titleColor(enabled: Boolean, focused: Boolean): Color {
        return if (enabled) {
            if (focused) {
                focusedTitleColor
            } else {
                titleColor
            }
        } else {
            disabledTitleColor
        }
    }

    fun textColor(enabled: Boolean, focused: Boolean): Color {
        return if (enabled) {
            if (focused) {
                focusedTextColor
            } else {
                textColor
            }
        } else {
            disabledTextColor
        }
    }

    fun lineColor(enabled: Boolean, focused: Boolean): Color {
        return if (enabled) {
            if (focused) {
                focusedLineColor
            } else {
                lineColor
            }
        } else {
            disabledLineColor
        }
    }

    fun passwordColor(showPassword: Boolean): Color {
        return if (showPassword) {
            showPasswordColor
        } else {
            hidePasswordColor
        }
    }
}