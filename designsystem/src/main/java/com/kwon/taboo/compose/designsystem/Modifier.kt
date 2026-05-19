package com.kwon.taboo.compose.designsystem

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
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

@Composable
fun Modifier.scalePointerInput(
    onClick: () -> Unit,
    interactionSource: MutableInteractionSource? = remember { MutableInteractionSource() },
    enabled: Boolean = true
) : Modifier {
    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f
    )

    return scale(scale)
        .pointerInput(Unit) {
            detectTapGestures(
                onPress = {
                    if (!enabled) return@detectTapGestures

                    isPressed = true

                    val pressInteraction = PressInteraction.Press(Offset.Zero)
                    interactionSource?.tryEmit(
                        interaction = pressInteraction
                    )

                    try {
                        awaitRelease()
                        onClick()
                    } catch (e: Exception) {
                        // 드래그와 같은 이벤트에서 취소된 경우
                    } finally {
                        isPressed = false
                        interactionSource?.tryEmit(
                            interaction = PressInteraction.Release(pressInteraction)
                        )
                    }
                }
            )
        }
}