package com.kwon.taboo.compose.designsystem

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
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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

fun Modifier.verticalFadingEdge(
    top: Boolean,
    bottom: Boolean,
    fadeHeight: Dp = 24.dp,
    edgeAlpha: Float = 0.35f
): Modifier = this
    .graphicsLayer {
        compositingStrategy = CompositingStrategy.Offscreen
    }
    .drawWithContent {
        drawContent()

        val height = fadeHeight.toPx()
            .coerceAtMost(size.height / 2f)

        if (height <= 0f) return@drawWithContent

        val alpha = edgeAlpha.coerceIn(0f, 1f)

        if (top) {
            drawRect(
                brush = Brush.verticalGradient(
                    colorStops = arrayOf(
                        0f to Color.Black.copy(alpha = alpha),
                        0.4f to Color.Black.copy(alpha = 0.75f),
                        1f to Color.Black
                    ),
                    startY = 0f,
                    endY = height
                ),
                size = Size(size.width, height),
                blendMode = BlendMode.DstIn
            )
        }

        if (bottom) {
            drawRect(
                brush = Brush.verticalGradient(
                    colorStops = arrayOf(
                        0f to Color.Black,
                        0.6f to Color.Black.copy(alpha = 0.75f),
                        1f to Color.Black.copy(alpha = alpha)
                    ),
                    startY = size.height - height,
                    endY = size.height
                ),
                topLeft = Offset(
                    x = 0f,
                    y = size.height - height
                ),
                size = Size(size.width, height),
                blendMode = BlendMode.DstIn
            )
        }
    }

fun Modifier.horizontalFadingEdge(
    start: Boolean,
    end: Boolean,
    fadeWidth: Dp = 24.dp,
    edgeAlpha: Float = 0.35f
): Modifier = this
    .graphicsLayer {
        compositingStrategy = CompositingStrategy.Offscreen
    }
    .drawWithContent {
        drawContent()

        val width = fadeWidth.toPx()
            .coerceAtMost(size.width / 2f)

        if (width <= 0f) return@drawWithContent

        val alpha = edgeAlpha.coerceIn(0f, 1f)

        if (start) {
            drawRect(
                brush = Brush.horizontalGradient(
                    colorStops = arrayOf(
                        0f to Color.Black.copy(alpha = alpha),
                        0.4f to Color.Black.copy(alpha = 0.75f),
                        1f to Color.Black
                    ),
                    startX = 0f,
                    endX = width
                ),
                size = Size(width, size.height),
                blendMode = BlendMode.DstIn
            )
        }

        if (end) {
            drawRect(
                brush = Brush.horizontalGradient(
                    colorStops = arrayOf(
                        0f to Color.Black,
                        0.6f to Color.Black.copy(alpha = 0.75f),
                        1f to Color.Black.copy(alpha = alpha)
                    ),
                    startX = size.width - width,
                    endX = size.width
                ),
                topLeft = Offset(
                    x = size.width - width,
                    y = 0f
                ),
                size = Size(width, size.height),
                blendMode = BlendMode.DstIn
            )
        }
    }
