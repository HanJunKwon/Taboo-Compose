package com.kwon.taboo.compose.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

/**
 * 라이트 테마의 색상 스키마
 */
val LightDefaultColorScheme = lightColorScheme(
    primary = TabooBlue600,
    onPrimary = White,
    secondary = TabooBlue100,
    onSecondary = TabooBlue600,
    tertiary = TabooGray600,
    onTertiary = TabooGray200,
    error = TabooRed600,
    onError = White,
    background = Color.White,
    onBackground = TabooBlack900
)

/**
 * 다크 테마의 색상 스키마
 */
val DarkDefaultColorScheme = darkColorScheme(
    primary = TabooBlue600,
    onPrimary = White,
    secondary = TabooBlue100,
    onSecondary = TabooBlue600,
    tertiary = Color.Transparent,
    onTertiary = White,
    error = TabooRed600,
    onError = White,
    background = Color.Black,
    onBackground = White
)

/**
 * 라이트 테마의 배경 색상
 */
val LightBackgroundTheme = TabooBackgroundTheme(color = Color.White)

/**
 * 다크 테마의 배경 색상
 */
val DarkBackgroundTheme = TabooBackgroundTheme(color = Color.Black)

/**
 * Taboo 라이브러리의 테마를 정의.
 */
@Composable
fun TabooTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkDefaultColorScheme else LightDefaultColorScheme

    val backgroundTheme = if (darkTheme) DarkBackgroundTheme else LightBackgroundTheme

    CompositionLocalProvider(
        LocalBackgroundTheme provides backgroundTheme
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content
        )
    }
}