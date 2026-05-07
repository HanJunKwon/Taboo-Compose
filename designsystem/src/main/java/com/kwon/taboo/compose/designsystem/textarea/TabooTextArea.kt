package com.kwon.taboo.compose.designsystem.textarea

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kwon.taboo.compose.designsystem.TabooBackground
import com.kwon.taboo.compose.designsystem.ThemePreviews
import com.kwon.taboo.compose.designsystem.theme.TabooBlack600
import com.kwon.taboo.compose.designsystem.theme.TabooBlack700
import com.kwon.taboo.compose.designsystem.theme.TabooBlack800
import com.kwon.taboo.compose.designsystem.theme.TabooBlack900
import com.kwon.taboo.compose.designsystem.theme.TabooBlue100
import com.kwon.taboo.compose.designsystem.theme.TabooBlue900
import com.kwon.taboo.compose.designsystem.theme.TabooFontFamily
import com.kwon.taboo.compose.designsystem.theme.TabooGray100
import com.kwon.taboo.compose.designsystem.theme.TabooGray200
import com.kwon.taboo.compose.designsystem.theme.TabooGray300
import com.kwon.taboo.compose.designsystem.theme.TabooGray400
import com.kwon.taboo.compose.designsystem.theme.TabooGray600
import com.kwon.taboo.compose.designsystem.theme.TabooTheme

@Composable
fun TabooTextArea(
    modifier: Modifier = Modifier,
    height: Dp = Dp.Unspecified,
    minHeight: Dp = 54.dp,
    state: TextFieldState,
    enabled: Boolean = true,
    placeHolder: String? = null,
    help: String? = null,
    colors: TabooTextAreaColors = TabooTextAreaDefault.defaultColors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val focused = interactionSource.collectIsFocusedAsState().value

    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = minHeight)
                .height(height)
                .background(
                    color = colors.backgroundColor(
                        enabled = enabled,
                        focused = focused
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(
                    vertical = 15.dp,
                    horizontal = 12.dp
                )
        ) {
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                ,
                state = state,
                interactionSource = interactionSource,
                enabled = enabled,
                textStyle = TextStyle(
                    fontFamily = TabooFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = colors.textColor(enabled)
                ),
                decorator = { innerTextField ->
                    if (state.text.isEmpty() && placeHolder != null) {
                        Text(
                            text = placeHolder,
                            style = TextStyle(
                                fontFamily = TabooFontFamily,
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp,
                                color = colors.placeHolderColor
                            )
                        )
                    }

                    innerTextField()
                }
            )
        }

        if (help != null) {
            Text(
                modifier = Modifier.padding(top = 2.dp),
                text = help,
                style = TextStyle(
                    fontFamily = TabooFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = colors.helpTextColor
                )
            )
        }
    }
}

object TabooTextAreaDefault {
    @Composable
    fun defaultColors() = TabooTextAreaColors(
        textColor = textColor(),
        disabledTextColor = disabledTextColor(),
        placeHolderColor = placeHolderColor(),
        helpTextColor = helpTextColor(),
        backgroundColor = backgroundColor(),
        disabledBackgroundColor = disabledBackgroundColor(),
        focusedBackgroundColor = focusedBackgroundColor()
    )

    @Composable
    private fun textColor(): Color {
        return if (isSystemInDarkTheme()) TabooGray300 else Color.Black
    }

    @Composable
    private fun disabledTextColor() : Color {
        return if (isSystemInDarkTheme()) TabooGray600 else TabooGray600
    }

    @Composable
    private fun placeHolderColor() : Color {
        return if (isSystemInDarkTheme()) TabooGray600 else TabooGray400
    }

    @Composable
    private fun helpTextColor() : Color {
        return if (isSystemInDarkTheme()) TabooGray600 else TabooGray400
    }

    @Composable
    private fun backgroundColor() : Color {
        return if (isSystemInDarkTheme()) TabooBlack800 else TabooGray100
    }

    @Composable
    private fun disabledBackgroundColor() : Color {
        return if (isSystemInDarkTheme()) TabooBlack900 else TabooGray200
    }

    @Composable
    private fun focusedBackgroundColor() : Color {
        return if (isSystemInDarkTheme()) TabooBlack800 else TabooBlue100
    }
}

@ThemePreviews
@Composable
fun TabooTextAreaRowPreviews() {
    TabooTheme() {
        TabooBackground {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    TabooTextArea(
                        state = TextFieldState(""),
                        height = 100.dp,
                        placeHolder = "Password",
                        help = "도움말 표시 영역입니다."
                    )
                    TabooTextArea(
                        state = TextFieldState(""),
                        placeHolder = "비밀번호를 입력해주세요."
                    )
                    TabooTextArea(
                        state = TextFieldState(""),
                        placeHolder = ""
                    )

                    TabooTextArea(
                        state = TextFieldState(""),
                        placeHolder = "Password",
                        enabled = false
                    )
                    TabooTextArea(
                        state = TextFieldState(""),
                        placeHolder = "비밀번호를 입력해주세요.",
                        enabled = false
                    )
                    TabooTextArea(
                        state = TextFieldState(""),
                        placeHolder = "",
                        enabled = false
                    )
                }
            }
        }
    }
}