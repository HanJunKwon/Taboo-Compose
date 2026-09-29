package com.kwon.taboo.compose.designsystem.keypad

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.kwon.taboo.compose.designsystem.R
import com.kwon.taboo.compose.designsystem.TabooBackground
import com.kwon.taboo.compose.designsystem.ThemePreviews
import com.kwon.taboo.compose.designsystem.button.TabooButton
import com.kwon.taboo.compose.designsystem.button.TabooButtonColors
import com.kwon.taboo.compose.designsystem.theme.TabooBlack200
import com.kwon.taboo.compose.designsystem.theme.TabooGray100
import com.kwon.taboo.compose.designsystem.theme.TabooGray200
import com.kwon.taboo.compose.designsystem.theme.TabooGray300
import com.kwon.taboo.compose.designsystem.theme.TabooGray600
import com.kwon.taboo.compose.designsystem.theme.TabooOpacityWhiteW46
import com.kwon.taboo.compose.designsystem.theme.TabooOpacityWhiteW5
import com.kwon.taboo.compose.designsystem.theme.TabooTheme

@Composable
fun TabooIntKeyPad(
    modifier: Modifier = Modifier,
    onKeyClick: (KeypadKey) -> Unit,
    buttonColors: TabooButtonColors = TabooKeyPad.buttonColors()
) {
    Box(
        modifier = modifier.wrapContentHeight()
    ) {
        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                TabooButton(
                    modifier = Modifier.weight(1f),
                    text = {
                        Text(text = "1")
                    },
                    onClick = { onKeyClick(KeypadKey.Digit(1)) },
                    colors = buttonColors,
                )

                TabooButton(
                    modifier = Modifier.weight(1f),
                    text = {
                        Text(text = "2")
                    },
                    onClick = { onKeyClick(KeypadKey.Digit(2)) },
                    colors = buttonColors,
                )

                TabooButton(
                    modifier = Modifier.weight(1f),
                    text = {
                        Text(text = "3")
                    },
                    onClick = { onKeyClick(KeypadKey.Digit(3)) },
                    colors = buttonColors,
                )
            }

            Row(modifier = Modifier.fillMaxWidth()) {
                TabooButton(
                    modifier = Modifier.weight(1f),
                    text = {
                        Text(text = "4")
                    },
                    onClick = { onKeyClick(KeypadKey.Digit(4)) },
                    colors = buttonColors,
                )

                TabooButton(
                    modifier = Modifier.weight(1f),
                    text = {
                        Text(text = "5")
                    },
                    onClick = { onKeyClick(KeypadKey.Digit(5)) },
                    colors = buttonColors,
                )

                TabooButton(
                    modifier = Modifier.weight(1f),
                    text = {
                        Text(text = "6")
                    },
                    onClick = { onKeyClick(KeypadKey.Digit(6)) },
                    colors = buttonColors,
                )
            }

            Row(modifier = Modifier.fillMaxWidth()) {
                TabooButton(
                    modifier = Modifier.weight(1f),
                    text = {
                        Text(text = "7")
                    },
                    onClick = { onKeyClick(KeypadKey.Digit(7)) },
                    colors = buttonColors,
                )

                TabooButton(
                    modifier = Modifier.weight(1f),
                    text = {
                        Text(text = "8")
                    },
                    onClick = { onKeyClick(KeypadKey.Digit(8)) },
                    colors = buttonColors,
                )

                TabooButton(
                    modifier = Modifier.weight(1f),
                    text = {
                        Text(text = "9")
                    },
                    onClick = { onKeyClick(KeypadKey.Digit(9)) },
                    colors = buttonColors,
                )
            }

            Row(modifier = Modifier.fillMaxWidth()) {
                TabooButton(
                    modifier = Modifier.weight(1f),
                    text = {
                        Text(text = "")
                    },
                    onClick = {

                    },
                    colors = buttonColors,
                )

                TabooButton(
                    modifier = Modifier.weight(1f),
                    text = {
                        Text(text = "0")
                    },
                    onClick = { onKeyClick(KeypadKey.Digit(0)) },
                    colors = buttonColors,
                )

                TabooButton(
                    modifier = Modifier.weight(1f),
                    text = {
                        Icon(painterResource(R.drawable.ic_arrow_back), contentDescription = "")
                    },
                    onClick = { onKeyClick(KeypadKey.Backspace) },
                    colors = buttonColors
                )
            }
        }
    }
}

object TabooKeyPad {
    @Composable
    fun buttonColors() = TabooButtonColors(
        containerColor = Color.Unspecified,
        contentColor = if (isSystemInDarkTheme()) TabooBlack200 else TabooGray600,
        pressedContainerColor = if (isSystemInDarkTheme()) TabooOpacityWhiteW5 else TabooGray200,
        pressedContentColor = if (isSystemInDarkTheme()) Color.White else TabooGray600,
        disableContainerColor = if (isSystemInDarkTheme()) Color.Unspecified else TabooGray100,
        disableContentColor = if (isSystemInDarkTheme()) TabooOpacityWhiteW46 else TabooGray300
    )
}

sealed interface KeypadKey {
    data class Digit(val value: Int): KeypadKey
    data object Backspace: KeypadKey
}

@ThemePreviews
@Composable
fun TabooIntKeyPadPreviews() {
    TabooTheme {
        TabooBackground {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                TabooIntKeyPad(
                    onKeyClick = {
                        Log.d(">>>", "key: ${it}")
                    }
                )
            }
        }
    }
}