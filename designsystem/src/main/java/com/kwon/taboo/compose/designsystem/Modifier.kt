package com.kwon.taboo.compose.designsystem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.kwon.taboo.compose.designsystem.indication.ScaleIndication

@Composable
fun Modifier.scaleClickable(
    onClick: () -> Unit,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = remember { MutableInteractionSource() }
): Modifier {
    val scaleIndication = ScaleIndication

    return clickable(
        onClick = onClick,
        enabled = enabled,
        indication = scaleIndication,
        interactionSource = interactionSource
    )
}