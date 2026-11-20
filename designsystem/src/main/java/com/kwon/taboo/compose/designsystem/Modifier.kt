package com.kwon.taboo.compose.designsystem

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
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
    interactionSource: MutableInteractionSource? =
        remember { MutableInteractionSource() },
    pressedScale: Float = 0.95f,
    enabled: Boolean = true
): Modifier {
    var isPressed by remember {
        mutableStateOf(false)
    }

    val scale by animateFloatAsState(
        targetValue = if (isPressed) pressedScale else 1f,
        label = "PressScale"
    )

    // Recomposition 이후에도 최신 값 참조
    val currentOnClick by rememberUpdatedState(onClick)
    val currentEnabled by rememberUpdatedState(enabled)
    val currentInteractionSource by rememberUpdatedState(interactionSource)

    return this
        .scale(scale)
        .pointerInput(Unit) {
            detectTapGestures(
                onPress = { offset ->
                    if (!currentEnabled) {
                        return@detectTapGestures
                    }

                    isPressed = true

                    val source = currentInteractionSource
                    val pressInteraction = PressInteraction.Press(
                        pressPosition = offset
                    )

                    source?.emit(pressInteraction)

                    val released = tryAwaitRelease()

                    isPressed = false

                    if (released) {
                        source?.emit(
                            PressInteraction.Release(pressInteraction)
                        )

                        currentOnClick()
                    } else {
                        source?.emit(
                            PressInteraction.Cancel(pressInteraction)
                        )
                    }
                }
            )
        }
}