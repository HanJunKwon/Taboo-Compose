package com.kwon.taboo.compose.designsystem.progress

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kwon.taboo.compose.designsystem.TabooBackground
import com.kwon.taboo.compose.designsystem.TabooShape
import com.kwon.taboo.compose.designsystem.ThemePreviews
import com.kwon.taboo.compose.designsystem.theme.TabooBlue600
import com.kwon.taboo.compose.designsystem.theme.TabooGray400
import com.kwon.taboo.compose.designsystem.theme.TabooTheme

@Composable
fun TabooProgress(
    modifier: Modifier = Modifier,
    size: TabooProgressSize = TabooProgressSize.MEDIUM,
    colors: TabooProgressColors = TabooProgressDefaults.colors(),
    gap: Float = 0f
) {
    val progressWidth = TabooProgressDefaults.width(size)

    Box(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .height(progressWidth)
                .fillMaxWidth()
                .background(
                    color = colors.trackColor,
                    shape = TabooShape.ExtraLarge
                )
        )

        Box(
            modifier = Modifier
                .height(progressWidth)
                .fillMaxWidth(gap)
                .background(
                    color = colors.indicatorColor,
                    shape = TabooShape.ExtraLarge
                )
        )

    }
}

object TabooProgressDefaults {
    @Composable
    fun width(size: TabooProgressSize): Dp {
        return when (size) {
            TabooProgressSize.SMALL -> 2.dp
            TabooProgressSize.MEDIUM -> 5.dp
            TabooProgressSize.LARGE -> 10.dp
        }
    }

    @Composable
    fun colors(): TabooProgressColors {
        return TabooProgressColors(
            trackColor = TabooGray400,
            indicatorColor = TabooBlue600
        )
    }
}

enum class TabooProgressSize {
    SMALL,
    MEDIUM,
    LARGE
}

@ThemePreviews
@Composable
fun TabooProgressPreviews() {
    TabooTheme {
        TabooBackground {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                TabooProgress(
                    size = TabooProgressSize.SMALL
                )
                TabooProgress(
                    gap = 0.26f
                )
                TabooProgress(
                    size = TabooProgressSize.LARGE,
                    gap = 1.26f
                )
            }
        }
    }
}