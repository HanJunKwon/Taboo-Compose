package com.kwon.taboo.compose.designsystem.chip

import androidx.compose.ui.graphics.Color

data class ChipColors(
    val textColor: Color,
    val selectedTextColor: Color,
    val backgroundColor: Color,
    val selectedBackgroundColor: Color
) {
    fun textColor(isSelected: Boolean) : Color {
        return if (isSelected) selectedTextColor else textColor
    }

    fun backgroundColor(isSelected: Boolean) : Color {
        return if (isSelected) selectedBackgroundColor else backgroundColor
    }
}
