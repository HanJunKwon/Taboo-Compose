package com.kwon.taboo.compose.designsystem.loading

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kwon.taboo.compose.designsystem.TabooBackground
import com.kwon.taboo.compose.designsystem.ThemePreviews
import com.kwon.taboo.compose.designsystem.theme.TabooBlue600
import com.kwon.taboo.compose.designsystem.theme.TabooTheme

@Composable
fun DotLoading(
    dotSize: Dp = 10.dp,
    dotColor: Color = Color.White,
    dotSpace: Dp = 8.dp
) {

    Row(
        horizontalArrangement = Arrangement
            .spacedBy(dotSpace)
    ) {
        Dot(
            dotSize = dotSize,
            dotColor = dotColor
        )

        Dot(
            dotSize = dotSize,
            dotColor = dotColor,
            animationDelay = 80
        )
        Dot(
            dotSize = dotSize,
            dotColor = dotColor,
            animationDelay = 160
        )
    }
}

@Composable
fun Dot(
    dotSize: Dp = 10.dp,
    dotColor: Color = Color.White,
    animationDelay: Int = 0
) {
    val scaleInfinity = rememberInfiniteTransition(label = "scale")
    val scale by scaleInfinity.animateFloat(
        initialValue = 1f,
        targetValue = 0.7f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 240, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
            initialStartOffset = StartOffset(animationDelay)
        )
    )

    val alphaInfinity = rememberInfiniteTransition("alpha")
    val alpha by alphaInfinity.animateFloat(
        initialValue = 1f,
        targetValue = 0.18f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 240, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
            initialStartOffset = StartOffset(animationDelay)
        )
    )


    Box(
        modifier = Modifier
            .size(dotSize)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                this.alpha = alpha
            }
            .background(
                color = dotColor,
                shape = CircleShape
            )
    )
}

@ThemePreviews
@Composable
fun DotPreviews() {
    TabooTheme() {
        TabooBackground {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .background(color = TabooBlue600)
            ) {
                Dot()
            }
        }
    }
}

@ThemePreviews
@Composable
fun DotLoadingPreviews() {
    TabooTheme() {
        TabooBackground {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .background(color = TabooBlue600)
            ) {
                DotLoading()
                DotLoading(
                    dotColor = Color.Black
                )
            }
        }
    }
}