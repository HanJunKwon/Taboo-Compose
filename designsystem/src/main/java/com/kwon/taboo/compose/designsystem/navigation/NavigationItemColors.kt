package com.kwon.taboo.compose.designsystem.navigation

import androidx.compose.ui.graphics.Color

class NavigationItemColors(
    val selectedColor: Color,
    val unselectedColor: Color
) {
    fun textColor(isSelected: Boolean): Color {
        return if (isSelected) selectedColor else unselectedColor
    }

    fun iconColor(isSelected: Boolean): Color {
        return if (isSelected) selectedColor else unselectedColor
    }
}