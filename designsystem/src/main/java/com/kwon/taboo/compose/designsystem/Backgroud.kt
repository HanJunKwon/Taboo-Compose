package com.kwon.taboo.compose.designsystem

import android.content.res.Configuration
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kwon.taboo.compose.designsystem.theme.LocalBackgroundTheme

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light Theme", showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Theme", showBackground = true)
annotation class ThemePreviews

@Composable
fun TabooBackground(
    content: @Composable () -> Unit
) {
    val color = LocalBackgroundTheme.current.color

    Surface(
        color = color
    ) {
        content()
    }
}