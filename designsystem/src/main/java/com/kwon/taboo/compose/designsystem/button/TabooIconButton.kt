package com.kwon.taboo.compose.designsystem.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kwon.taboo.compose.designsystem.R
import com.kwon.taboo.compose.designsystem.TabooBackground
import com.kwon.taboo.compose.designsystem.TabooShape
import com.kwon.taboo.compose.designsystem.ThemePreviews
import com.kwon.taboo.compose.designsystem.scalePointerInput
import com.kwon.taboo.compose.designsystem.theme.TabooBlack700
import com.kwon.taboo.compose.designsystem.theme.TabooBlack800
import com.kwon.taboo.compose.designsystem.theme.TabooGray100
import com.kwon.taboo.compose.designsystem.theme.TabooGray200
import com.kwon.taboo.compose.designsystem.theme.TabooGray500
import com.kwon.taboo.compose.designsystem.theme.TabooGreen100
import com.kwon.taboo.compose.designsystem.theme.TabooRed600
import com.kwon.taboo.compose.designsystem.theme.TabooTheme
import com.kwon.taboo.compose.designsystem.theme.TabooYellow100

@Composable
fun TabooIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    size: TabooIconButtonSize = TabooIconButtonSize.MEDIUM,
    enabled: Boolean = true,
    icon: Painter,
    variant: TabooIconButtonVariant = TabooIconButtonVariant.CLEAR,
    colors: TabooIconButtonColors = TabooIconButtonDefaults.colors(variant)
) {
    val iconSize = when (size) {
        TabooIconButtonSize.SMALL -> 16.dp
        TabooIconButtonSize.MEDIUM -> 20.dp
        TabooIconButtonSize.LARGE -> 24.dp
    }

    val boxSize = when (size) {
        TabooIconButtonSize.SMALL -> 32.dp
        TabooIconButtonSize.MEDIUM -> 38.dp
        TabooIconButtonSize.LARGE -> 48.dp
    }

    val shape = when (size) {
        TabooIconButtonSize.SMALL -> TabooShape.Medium
        TabooIconButtonSize.MEDIUM -> TabooShape.Large
        TabooIconButtonSize.LARGE -> TabooShape.ExtraLarge
    }

    val isPressed = interactionSource.collectIsPressedAsState().value

    Box(
        modifier = modifier
            .scalePointerInput(
                onClick = onClick,
                enabled = enabled,
                interactionSource = interactionSource,
                pressedScale = 0.9f
            )
            .size(boxSize)
            .background(
                color = if (isPressed) colors.pressedBackgroundColor
                        else colors.backgroundColor,
                shape = shape
            )
            .border(
                width = 1.dp,
                color = colors.borderColor,
                shape = shape
            )
        ,
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = icon,
            contentDescription = "",
            modifier = Modifier.size(iconSize),
            colorFilter = ColorFilter.tint(color = colors.iconColor),
        )
    }
}

object TabooIconButtonDefaults {
    @Composable
    fun colors(variant: TabooIconButtonVariant): TabooIconButtonColors {
        return TabooIconButtonColors(
            backgroundColor = defaultBackgroundColor(variant),
            pressedBackgroundColor = defaultPressedBackgroundColor(variant),
            borderColor = defaultBorderColor(variant),
            iconColor = defaultIconColor()
        )
    }

    @Composable
    private fun defaultBackgroundColor(
        variant: TabooIconButtonVariant
    ): Color {
        return when (variant) {
            TabooIconButtonVariant.CLEAR -> Color.Transparent

            TabooIconButtonVariant.FILL -> if (isSystemInDarkTheme()) TabooBlack800 else TabooGray100

            TabooIconButtonVariant.OUTLINE -> Color.Transparent
        }
    }

    @Composable
    private fun defaultPressedBackgroundColor(variant: TabooIconButtonVariant): Color {
        return when (variant) {
            TabooIconButtonVariant.CLEAR -> if (isSystemInDarkTheme()) TabooBlack800 else TabooGray100

            TabooIconButtonVariant.FILL -> if (isSystemInDarkTheme()) TabooBlack700 else TabooGray200

            TabooIconButtonVariant.OUTLINE -> if (isSystemInDarkTheme()) TabooBlack800 else TabooGray100
        }
    }

    @Composable
    private fun defaultBorderColor(variant: TabooIconButtonVariant): Color {
        return when (variant) {
            TabooIconButtonVariant.CLEAR -> Color.Transparent

            TabooIconButtonVariant.FILL -> Color.Transparent

            TabooIconButtonVariant.OUTLINE -> if (isSystemInDarkTheme()) TabooBlack800 else TabooGray100
        }
    }

    @Composable
    private fun defaultIconColor(): Color {
        return if (isSystemInDarkTheme()) TabooGray500 else TabooGray500
    }
}

enum class TabooIconButtonSize {
    SMALL,
    MEDIUM,
    LARGE
}

enum class TabooIconButtonVariant {
    CLEAR,
    FILL,
    OUTLINE
}


@ThemePreviews
@Composable
private fun TabooIconButtonPreview() {
    TabooTheme {
        TabooBackground {
            Column {
                Row(
                    modifier = Modifier.padding(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TabooIconButton(
                        onClick = {

                        },
                        size = TabooIconButtonSize.SMALL,
                        icon = painterResource(R.drawable.ic_search)
                    )

                    TabooIconButton(
                        onClick = {

                        },
                        icon = painterResource(R.drawable.ic_search)
                    )

                    TabooIconButton(
                        onClick = {

                        },
                        size = TabooIconButtonSize.LARGE,
                        icon = painterResource(R.drawable.ic_search)
                    )
                }

                Row(
                    modifier = Modifier.padding(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TabooIconButton(
                        onClick = {

                        },
                        size = TabooIconButtonSize.SMALL,
                        icon = painterResource(R.drawable.ic_search),
                        variant = TabooIconButtonVariant.FILL
                    )

                    TabooIconButton(
                        onClick = {

                        },
                        icon = painterResource(R.drawable.ic_search),
                        variant = TabooIconButtonVariant.FILL
                    )

                    TabooIconButton(
                        onClick = {

                        },
                        size = TabooIconButtonSize.LARGE,
                        icon = painterResource(R.drawable.ic_search),
                        variant = TabooIconButtonVariant.FILL
                    )
                }

                Row(
                    modifier = Modifier.padding(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TabooIconButton(
                        onClick = {

                        },
                        size = TabooIconButtonSize.SMALL,
                        icon = painterResource(R.drawable.ic_search),
                        variant = TabooIconButtonVariant.OUTLINE
                    )

                    TabooIconButton(
                        onClick = {

                        },
                        icon = painterResource(R.drawable.ic_search),
                        variant = TabooIconButtonVariant.OUTLINE
                    )

                    TabooIconButton(
                        onClick = {

                        },
                        size = TabooIconButtonSize.LARGE,
                        icon = painterResource(R.drawable.ic_search),
                        variant = TabooIconButtonVariant.OUTLINE
                    )
                }

                Row(
                    modifier = Modifier.padding(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    TabooIconButton(
                        onClick = {

                        },
                        icon = painterResource(R.drawable.baseline_article_24)
                    )

                    TabooIconButton(
                        onClick = {

                        },
                        icon = painterResource(R.drawable.baseline_article_24),
                        variant = TabooIconButtonVariant.FILL
                    )

                    TabooIconButton(
                        onClick = {

                        },
                        icon = painterResource(R.drawable.baseline_article_24),
                        variant = TabooIconButtonVariant.OUTLINE
                    )
                }

                Row(
                    modifier = Modifier.padding(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    TabooIconButton(
                        onClick = {

                        },
                        icon = painterResource(R.drawable.baseline_article_24),
                        colors = TabooIconButtonDefaults.colors(variant = TabooIconButtonVariant.CLEAR).copy(
                            iconColor = TabooRed600
                        )
                    )

                    TabooIconButton(
                        onClick = {

                        },
                        icon = painterResource(R.drawable.baseline_article_24),
                        variant = TabooIconButtonVariant.FILL,
                        colors = TabooIconButtonDefaults.colors(variant = TabooIconButtonVariant.FILL).copy(
                            iconColor = TabooYellow100
                        )
                    )

                    TabooIconButton(
                        onClick = {

                        },
                        icon = painterResource(R.drawable.baseline_article_24),
                        variant = TabooIconButtonVariant.OUTLINE,
                        colors = TabooIconButtonDefaults.colors(variant = TabooIconButtonVariant.OUTLINE).copy(
                            iconColor = TabooGreen100
                        )
                    )
                }
            }
        }
    }
}