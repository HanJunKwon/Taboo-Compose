package com.kwon.taboo.compose.designsystem.loading

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kwon.taboo.compose.designsystem.TabooBackground
import com.kwon.taboo.compose.designsystem.ThemePreviews
import com.kwon.taboo.compose.designsystem.theme.TabooBlack900
import com.kwon.taboo.compose.designsystem.theme.TabooBlue600
import com.kwon.taboo.compose.designsystem.theme.TabooGreen200
import com.kwon.taboo.compose.designsystem.theme.TabooTheme

/**
 * @param trackColor
 * @param startAngle 시작 각도 (3시 방향이 0도, 시계방향 증가. 180도는 9시 방향)
 * @param sweepAngle 회전할 각도 (180도만큼 회전하여 반원 곡선 생성)
 */
@Composable
fun TabooCircularLoading(
    trackColor: Color = TabooCircularLoadingDefault.trackColor(),
    colors: List<Color> = TabooCircularLoadingDefault.indicatorColors(),
    startAngle: Float = 25f,
    sweepAngle: Float = 180f,
    durationMillis: Int = 1500,
    loadingSize: TabooCircularLoadingSize = TabooCircularLoadingSize.MEDIUM
) {
    val diameter = TabooCircularLoadingDefault.diameter(size = loadingSize)
    val strokeWidth = TabooCircularLoadingDefault.strokeWidth(size = loadingSize)
    val rotateInfiniteTransition = rememberInfiniteTransition(label = "rotateInfiniteTransition")
    val rotate by rotateInfiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis)
        ),
        label = "rotate"
    )

    Canvas(
        modifier = Modifier
            .padding(10.dp)
            .size(diameter)
    ) {
        drawCircle(
            color = trackColor,
            style = Stroke(
                width = strokeWidth
            )
        )

        val arcSize = diameter.toPx()
        val topLeftOffset = Offset(
            x = (size.width - arcSize) / 2,
            y = (size.height - arcSize) / 2
        )

        rotate(
            degrees = rotate,
            pivot = center
        ) {
            drawArc(
                brush = Brush.linearGradient(colors = colors,),
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                topLeft = topLeftOffset,
                size = Size(arcSize, arcSize),
                style = Stroke(
                    width = strokeWidth,
                    cap = StrokeCap.Round
                )
            )
        }
    }
}

object TabooCircularLoadingDefault {
    fun trackColor(): Color {
        return TabooBlack900
    }

    fun indicatorColors(): List<Color> {
        return listOf(TabooBlue600, TabooGreen200)
    }

    fun diameter(size : TabooCircularLoadingSize): Dp {
        return when (size) {
            TabooCircularLoadingSize.SMALL -> 60.dp
            TabooCircularLoadingSize.MEDIUM -> 120.dp
            TabooCircularLoadingSize.LARGE -> 200.dp
        }
    }

    fun strokeWidth(size: TabooCircularLoadingSize): Float {
        return when (size) {
            TabooCircularLoadingSize.SMALL -> 25f
            TabooCircularLoadingSize.MEDIUM -> 35f
            TabooCircularLoadingSize.LARGE -> 45f
        }
    }
}

enum class TabooCircularLoadingSize {
    SMALL,
    MEDIUM,
    LARGE
}

@ThemePreviews
@Composable
private fun TabooCircularLoadingPreviews() {
    TabooTheme {
        TabooBackground {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    TabooCircularLoading(
                        loadingSize = TabooCircularLoadingSize.LARGE,
                        durationMillis = 1000
                    )
                }

                Box(
                    contentAlignment = Alignment.Center
                ) {
                    TabooCircularLoading(
                        loadingSize = TabooCircularLoadingSize.MEDIUM
                    )
                }

                Box(
                    contentAlignment = Alignment.Center
                ) {
                    TabooCircularLoading(
                        loadingSize = TabooCircularLoadingSize.SMALL,
                        durationMillis = 2000
                    )
                }
            }
        }
    }
}