package com.kwon.taboo.compose.designsystem.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kwon.taboo.compose.designsystem.TabooBackground
import com.kwon.taboo.compose.designsystem.ThemePreviews
import com.kwon.taboo.compose.designsystem.indication.ScaleIndication
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
    colors: TabooButtonColors = TabooButtonDefaults.buttonColors()
) {
    val isPressed = interactionSource.collectIsPressedAsState()

    Surface(
        modifier = modifier
            .heightIn(50.dp)
            .clickable(
                onClick = onClick,
                enabled = enabled,
                indication = ScaleIndication,
                interactionSource = interactionSource
            ).hoverable(
                interactionSource = interactionSource
            ),
        shape = RoundedCornerShape(size = 10.dp),
        color = colors.containerColor(
            enabled = enabled,
            isPressed = isPressed.value
        ),
        contentColor = colors.contentColor(
            enabled = enabled,
            isPressed = isPressed.value
        )
    ) {
        Box(
            modifier = Modifier
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ) {
            text()
        }
    }
}

object TabooButtonDefaults {
    @Composable
    fun buttonColors() = TabooButtonColors(
        containerColor = defaultContainerColor(),
        contentColor = defaultContentColor(),
        pressedContainerColor = defaultPressedContainerColor(),
        pressedContentColor = defaultPressedContentColor(),
        disableContainerColor = defaultDisableContainerColor(),
        disableContentColor = defaultDiableContentColor(),
    )

    private fun defaultContainerColor() = TabooBlue600

    private fun defaultContentColor() = White

    private fun defaultPressedContainerColor() = TabooBlue700

    private fun defaultPressedContentColor() = TabooGray200

    @Composable
    private fun defaultDisableContainerColor() = if (isSystemInDarkTheme()) TabooBlue900 else TabooBlue200

    @Composable
    private fun defaultDiableContentColor() = if (isSystemInDarkTheme()) TabooGray300 else White
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