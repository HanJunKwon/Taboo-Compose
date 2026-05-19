package com.kwon.taboo.compose.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class TabooBackgroundTheme(
    val color: Color = Color.Unspecified
)

val LocalBackgroundTheme = staticCompositionLocalOf { TabooBackgroundTheme() }