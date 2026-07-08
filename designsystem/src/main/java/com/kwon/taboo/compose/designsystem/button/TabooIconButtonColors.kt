package com.kwon.taboo.compose.designsystem.button

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class TabooIconButtonColors(
    val backgroundColor: Color,
    val pressedBackgroundColor: Color,
    val borderColor: Color,
    val iconColor: Color
)
