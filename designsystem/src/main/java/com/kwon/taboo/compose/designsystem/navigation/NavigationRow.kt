package com.kwon.taboo.compose.designsystem.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.kwon.taboo.compose.designsystem.button.TabooIconButton
import com.kwon.taboo.compose.designsystem.theme.TabooGray100
import com.kwon.taboo.compose.designsystem.theme.TabooGray400
import com.kwon.taboo.compose.designsystem.theme.TabooGray800

@Composable
fun NavigationRow(
    backIcon: Painter? = null,
    onBack: (() -> Unit)? = null,
    iconBackgroundColor: Color,
    iconColorFilter: ColorFilter,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .background(color = Color.Transparent),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (backIcon != null) {
            TabooIconButton(
                onClick = {
                    onBack?.invoke()
                },
                modifier = Modifier
                    .padding(start = 5.dp)
                    .background(
                        color = iconBackgroundColor,
                        shape = CircleShape
                    ),
                icon = backIcon,
                iconColorFilter = iconColorFilter
            )
        }

        Row(
            modifier = Modifier.padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            content()
        }
    }
}

object NavigationRowDefault {
    @Composable
    fun iconBackgroundColor(): Color {
        return if (isSystemInDarkTheme()) TabooGray800 else TabooGray100
    }

    @Composable
    fun iconColorFilter(): ColorFilter {
        return ColorFilter.tint(color = TabooGray400)
    }
}