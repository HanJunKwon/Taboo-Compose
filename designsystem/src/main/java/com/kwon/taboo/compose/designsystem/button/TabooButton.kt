package com.kwon.taboo.compose.designsystem.button

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kwon.taboo.compose.designsystem.TabooBackground
import com.kwon.taboo.compose.designsystem.TabooShape
import com.kwon.taboo.compose.designsystem.ThemePreviews
import com.kwon.taboo.compose.designsystem.loading.DotLoading
import com.kwon.taboo.compose.designsystem.scalePointerInput
import com.kwon.taboo.compose.designsystem.theme.TabooBlue200
import com.kwon.taboo.compose.designsystem.theme.TabooBlue600
import com.kwon.taboo.compose.designsystem.theme.TabooBlue700
import com.kwon.taboo.compose.designsystem.theme.TabooBlue900
import com.kwon.taboo.compose.designsystem.theme.TabooGray200
import com.kwon.taboo.compose.designsystem.theme.TabooGray300
import com.kwon.taboo.compose.designsystem.theme.TabooTheme
import com.kwon.taboo.compose.designsystem.theme.White

@Composable
fun TabooButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: @Composable () -> Unit,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: TabooButtonColors = TabooButtonDefaults.buttonColors(),
    border: BorderStroke? = null,
    isLoading: Boolean = false
) {
    val isPressed = interactionSource.collectIsPressedAsState()

    Surface(
        modifier = modifier
            .wrapContentHeight()
            .scalePointerInput(
                onClick = onClick,
                interactionSource = interactionSource,
                enabled = enabled
            )
        ,
        shape = RoundedCornerShape(25),
        color = colors.containerColor(
            enabled = enabled,
            isPressed = isPressed.value
        ),
        contentColor = colors.contentColor(
            enabled = enabled,
            isPressed = isPressed.value
        ),
        border = border
    ) {
        Box(
            modifier = Modifier
                .heightIn(60.dp),
            contentAlignment = Alignment.Center
        ) {
            if (isLoading) {
                DotLoading()
            } else {
                text()
            }
        }
    }
}

@Composable
fun TabooOutlineButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: @Composable () -> Unit,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: TabooButtonColors = TabooButtonDefaults.outlineButtonColors(),
    border: BorderStroke = TabooButtonDefaults.outlineButtonBorder(enabled)
) = TabooButton(
    onClick = onClick,
    modifier = modifier,
    enabled = enabled,
    text = text,
    interactionSource = interactionSource,
    colors = colors,
    border = border
)

object TabooButtonDefaults {
    @Composable
    fun buttonColors() = TabooButtonColors(
        containerColor = defaultContainerColor(),
        contentColor = defaultContentColor(),
        pressedContainerColor = defaultPressContainerColor(),
        pressedContentColor = defaultPressContentColor(),
        disableContainerColor = defaultDisableContainerColor(),
        disableContentColor = defaultDiableContentColor(),
    )

    /**
     * [TabooButton]의 배경의 기본 색상.
     */
    private fun defaultContainerColor() = TabooBlue600

    /**
     * [TabooButton]의 콘텐츠의 기본 색상
     */
    private fun defaultContentColor() = White

    /**
     * [TabooButton]의 상태가 `Pressed` 일 때 배경의 기본 색상
     */
    private fun defaultPressContainerColor() = TabooBlue700

    /**
     * [TabooButton]의 상태가 `Pressed`일 때, 콘텐츠의 기본 색상
     */
    private fun defaultPressContentColor() = TabooGray200

    /**
     * [TabooButton]이 비활성화 일 때, 배경의 기본 색상.
     */
    @Composable
    private fun defaultDisableContainerColor() = if (isSystemInDarkTheme()) TabooBlue900 else TabooBlue200

    /**
     * [TabooButton]이 비활성화 일 때, 컨텐츠의 기본 색상.
     */
    @Composable
    private fun defaultDiableContentColor() = if (isSystemInDarkTheme()) TabooGray300 else White

    /**
     * [TabooOutlineButton]의 생상 스키
     */
    @Composable
    fun outlineButtonColors() = TabooButtonColors(
        containerColor = defaultOutlineContainerColor(),
        contentColor = defaultOutlineContentColor(),
        pressedContainerColor = defaultPressOutlineContainerColor(),
        pressedContentColor = defaultPressOutlineContentColor(),
        disableContainerColor = defaultDisableOutlineContainerColor(),
        disableContentColor = defaultDiableOutlineContentColor(),
    )
    /**
     *
     * [TabooOutlineButton]의 배경의 기본 색상.
     */
    private fun defaultOutlineContainerColor() = Color.Transparent

    /**
     * [TabooOutlineButton]의 콘텐츠의 기본 색상
     */
    private fun defaultOutlineContentColor() = TabooBlue600

    /**
     * [TabooOutlineButton]의 상태가 `Pressed` 일 때 배경의 기본 색상
     */
    private fun defaultPressOutlineContainerColor() = Color.Transparent

    /**
     * [TabooOutlineButton]의 상태가 `Pressed`일 때, 콘텐츠의 기본 색상
     */
    private fun defaultPressOutlineContentColor() = TabooBlue700

    /**
     * [TabooOutlineButton]이 비활성화 일 때, 배경의 기본 색상.
     */
    @Composable
    private fun defaultDisableOutlineContainerColor() = Color.Transparent

    /**
     * [TabooOutlineButton]이 비활성화 일 때, 컨텐츠의 기본 색상.
     */
    @Composable
    private fun defaultDiableOutlineContentColor() = if (isSystemInDarkTheme()) TabooBlue900 else TabooBlue200

    /**
     * [TabooOutlineButton]의 `BorderStroke` 두께 및 색상.
     */
    @Composable
    fun outlineButtonBorder(enabled: Boolean) = BorderStroke(
        width = 1.dp,
        color = if (isSystemInDarkTheme()) {
            if (enabled) TabooBlue700 else TabooBlue900
        } else {
            if (enabled) TabooBlue600 else TabooBlue200
        }
    )
}

@ThemePreviews
@Composable
fun TabooButtonPreview() {
    TabooTheme {
        TabooBackground {
            Column(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ){
                TabooButton(
                    onClick = {

                    },
                    modifier = Modifier.fillMaxWidth(),
                    text = { Text(text = "Button") }
                )
                TabooButton(
                    onClick = {

                    },
                    modifier = Modifier.fillMaxWidth(),
                    text = { Text(text = "Button") },
                    enabled = false
                )
            }
        }
    }
}

@ThemePreviews
@Composable
fun TabooOutlineButtonPreview() {
    TabooTheme {
        TabooBackground {
            Column(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ){
                TabooOutlineButton(
                    onClick = {

                    },
                    modifier = Modifier.fillMaxWidth(),
                    text = { Text(text = "Button") }
                )
                TabooOutlineButton(
                    onClick = {

                    },
                    modifier = Modifier.fillMaxWidth(),
                    text = { Text(text = "Button") },
                    enabled = false
                )
            }
        }
    }
}

@ThemePreviews
@Composable
fun TabooButtonPreviews() {
    TabooTheme {
        TabooBackground {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                TabooButton(
                    onClick = {

                    },
                    modifier = Modifier.fillMaxWidth(),
                    text = { Text(text = "Button") }
                )
            }
        }
    }
}

@ThemePreviews
@Composable
fun TabooLoadingButtonPreviews() {
    TabooTheme {
        TabooBackground {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                TabooButton(
                    onClick = {

                    },
                    modifier = Modifier.fillMaxWidth(),
                    text = { Text(text = "Button") },
                    isLoading = true
                )

                TabooButton(
                    onClick = {

                    },
                    modifier = Modifier.fillMaxWidth(),
                    text = { Text(text = "Button") },
                    isLoading = true,
                    enabled = false
                )
            }
        }
    }
}


@ThemePreviews
@Composable
fun TabooButtonLazyPreviews() {
    TabooTheme {
        TabooBackground {
            Surface(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
                LazyColumn() {
                    items(5) {
                        TabooButton(
                            onClick = {
                                Log.d(">>>", "click")
                            },
                            modifier = Modifier.fillMaxWidth(),
                            text = { Text(text = "Button") }
                        )
                    }
                }

                TabooButton(
                    onClick = {
                        Log.d(">>>", "click")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    text = { Text(text = "Button") }
                )
            }
        }
    }
}