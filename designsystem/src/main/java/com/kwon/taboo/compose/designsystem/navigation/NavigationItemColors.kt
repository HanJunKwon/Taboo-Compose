package com.kwon.taboo.compose.designsystem.navigation

import androidx.compose.ui.graphics.Color

class NavigationItemColors(
    val backgroundColor: Color,
    val pressBackgroundColor: Color,
    val selectedColor: Color,
    val unselectedColor: Color
) {
    fun backgroundColor(isPressed: Boolean): Color {
        return if (isPressed) pressBackgroundColor else backgroundColor
    }

    fun textColor(isSelected: Boolean): Color {
        return if (isSelected) selectedColor else unselectedColor
    }

    fun iconColor(isSelected: Boolean): Color {
        return if (isSelected) selectedColor else unselectedColor
    }
}