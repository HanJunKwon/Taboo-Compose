package com.kwon.taboo.compose.designsystem.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kwon.taboo.compose.designsystem.R
import com.kwon.taboo.compose.designsystem.TabooBackground
import com.kwon.taboo.compose.designsystem.ThemePreviews
import com.kwon.taboo.compose.designsystem.scalePointerInput
import com.kwon.taboo.compose.designsystem.theme.TabooBlack700
import com.kwon.taboo.compose.designsystem.theme.TabooBlack800
import com.kwon.taboo.compose.designsystem.theme.TabooFontFamily
import com.kwon.taboo.compose.designsystem.theme.TabooGray100
import com.kwon.taboo.compose.designsystem.theme.TabooGray200
import com.kwon.taboo.compose.designsystem.theme.TabooGray500
import com.kwon.taboo.compose.designsystem.theme.TabooTheme

/**
 * 텍스트 버튼은 버튼
 */
@Composable
fun TabooTextButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    label: String,
    textStyle: TextStyle = TabooTextButtonDefault.textStyle(),
    icon: Painter? = null,
    colors: TabooTextButtonColors = TabooTextButtonDefault.defaultColors(),
    enabled: Boolean = true
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState().value

    Surface(
        modifier = modifier.semantics { role = Role.Button },
        color = Color.Transparent
    ) {
        Row(
            modifier = Modifier
                .scalePointerInput(
                    onClick = onClick,
                    enabled = enabled,
                    interactionSource = interactionSource
                )
                .background(
                    color = colors.backgroundColor(enabled, isPressed),
                    shape = RoundedCornerShape(25)
                )
                .padding(3.dp)
                .padding(horizontal = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = label,
                style = textStyle,
                color = colors.textColor(enabled)
            )

            if (icon != null) {
                Image(
                    painter = icon,
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(
                        color = colors.iconColor(enabled)
                    )
                )
            }
        }
    }
}

object TabooTextButtonDefault {
    @Composable
    fun defaultColors() = TabooTextButtonColors(
        textColor = textColor(),
        disabledColor = disabledTextColor(),
        iconColor = iconColor(),
        disabledIconColor = disabledIconColor(),
        backgroundColor = Color.Transparent,
        pressedBackgroundColor = backgroundPressColor(),
        disabledBackgroundColor = Color.Transparent
    )

    @Composable
    private fun textColor() : Color {
        return if (isSystemInDarkTheme()) TabooGray500 else TabooBlack700
    }

    @Composable
    private fun disabledTextColor() : Color {
        return if (isSystemInDarkTheme()) TabooBlack700 else TabooGray200
    }

    @Composable
    private fun iconColor() : Color {
        return if (isSystemInDarkTheme()) TabooGray500 else TabooBlack700
    }

    @Composable
    private fun disabledIconColor() : Color {
        return if (isSystemInDarkTheme()) TabooBlack700 else TabooGray200
    }

    @Composable
    private fun backgroundPressColor() : Color {
        return if (isSystemInDarkTheme()) TabooBlack800 else TabooGray100
    }

    fun textStyle() : TextStyle {
        return TextStyle(
            fontFamily = TabooFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        )
    }
}

@ThemePreviews
@Composable
fun TabooTextButtonPreviews() {
    TabooTheme() {
        TabooBackground {
            Column(
                modifier = Modifier.fillMaxSize().statusBarsPadding()
            ) {
                TabooTextButton(
                    onClick = {},
                    label = "텍스트 버튼",
                )

                TabooTextButton(
                    onClick = {},
                    label = "텍스트 버튼",
                    enabled = false
                )

                TabooTextButton(
                    onClick = {},
                    label = "텍스트 버튼",
                    modifier = Modifier.fillMaxWidth(),
                    icon = painterResource(R.drawable.ic_chevron_right)
                )
            }
        }
    }
}

@ThemePreviews
@Composable
fun TabooTextButtonPreviews2() {
    TabooTheme() {
        TabooBackground {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                TabooTextButton(
                    onClick = {},
                    label = "텍스트 버튼",
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    icon = painterResource(R.drawable.ic_chevron_right)
                )

                TabooTextButton(
                    onClick = {},
                    label = "텍스트 버튼",
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    icon = painterResource(R.drawable.ic_chevron_right)
                )
            }
        }
    }
}