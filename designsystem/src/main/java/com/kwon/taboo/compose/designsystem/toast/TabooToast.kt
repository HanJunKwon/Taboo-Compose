package com.kwon.taboo.compose.designsystem.toast

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.kwon.taboo.compose.designsystem.R
import com.kwon.taboo.compose.designsystem.TabooBackground
import com.kwon.taboo.compose.designsystem.ThemePreviews
import com.kwon.taboo.compose.designsystem.button.TabooButton
import com.kwon.taboo.compose.designsystem.theme.TabooFontFamily
import com.kwon.taboo.compose.designsystem.theme.TabooGray700
import com.kwon.taboo.compose.designsystem.theme.TabooGray800
import com.kwon.taboo.compose.designsystem.theme.TabooTheme
import kotlinx.coroutines.launch

/**
 * @param state 토스트의 상태 정보. 토스트의 `message`와 `visible` 상태를 관리.
 * @param variant 토스트의 모양을 결정. `ROUNDED`는 둥근 모양, `CIRCLE`은 원형 모양.
 * @param icon 토스트에 표시할 이미지 리소스
 * @param text 토스트에 표시할 텍스트
 * @param position 토스트를 표시할 위치. `TOP`은 상단에 표시, `BOTTOM`은 하단에 표시.
 * @param toastHeight 토스트가 표시될 높이. 기본값은 40.dp
 * @param animateDuration 토스트의 애니메이션 지속 시간. 기본값은 250
 */
@Composable
fun TabooToast(
    state: TabooToastState,
    variant: TabooToastVariant = TabooToastVariant.ROUNDED,
    icon: Painter? = null,
    text: String,
    position: TabooToastPosition = TabooToastPosition.BOTTOM,
    toastHeight: Dp = 40.dp,
    animateDuration: Int = TabooToastDefaults.animationDuration()
) {
    val shape = TabooToastDefaults.backgroundShape(variant)

    Box(
        modifier = Modifier.fillMaxSize().safeDrawingPadding()
    ) {
        AnimatedVisibility(
            visible = state.isVisible,
            modifier = Modifier
                .background(color = Color.Transparent)
                .align(
                    when (position) {
                        TabooToastPosition.TOP -> Alignment.TopCenter
                        TabooToastPosition.BOTTOM -> Alignment.BottomCenter
                    }
                )
            ,
            enter = slideInVertically(
                initialOffsetY = {
                    when (position) {
                        TabooToastPosition.TOP -> -it
                        TabooToastPosition.BOTTOM -> it
                    }
                },
                animationSpec = tween(durationMillis = animateDuration)
            ) + fadeIn(initialAlpha = 0.3f),
            exit = slideOutVertically(
                targetOffsetY = {
                    when (position) {
                        TabooToastPosition.TOP -> -it
                        TabooToastPosition.BOTTOM -> it
                    }
                },
                animationSpec = tween(durationMillis = animateDuration)
            ) + fadeOut(targetAlpha = 0.3f)
        ) {
            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(vertical = toastHeight)
                    .dropShadow(
                        shape = shape,
                        shadow = Shadow(
                            radius = 20.dp,
                            spread = 0.dp,
                            color = Color(0xFF191F28).copy(alpha = 0.12f),
                            offset = DpOffset(x = 0.dp, y = 6.dp),
                        ),
                    )
                    // 배경 가까이에 생기는 작은 그림자
                    .dropShadow(
                        shape = shape,
                        shadow = Shadow(
                            radius = 5.dp,
                            spread = 0.dp,
                            color = Color(0xFF191F28).copy(alpha = 0.04f),
                            offset = DpOffset(x = 0.dp, y = 2.dp),
                        ),
                    )
                    .background(
                        color = TabooToastDefaults.backgroundColor(),
                        shape = TabooToastDefaults.backgroundShape(variant)
                    )
                    .padding(14.dp),
                contentAlignment = Alignment.Center
            ) {
                Row {
                    if (icon != null) {
                        Image(
                            painter = icon,
                            contentDescription = "",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(15.dp))
                    }

                    Text(
                        modifier = Modifier.wrapContentWidth(),
                        text = text,
                        color = TabooToastDefaults.textColor(),
                        style = TextStyle(
                            fontFamily = TabooFontFamily,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            }
        }
    }
}

enum class TabooToastPosition {
    TOP,
    BOTTOM
}

enum class TabooToastVariant {
    ROUNDED,
    CIRCLE
}

object TabooToastDefaults {
    private const val ANIMATION_DURATION = 250

    fun animationDuration() = ANIMATION_DURATION
    
    fun backgroundShape(variant: TabooToastVariant) = when (variant) {
        TabooToastVariant.ROUNDED -> RoundedCornerShape(12.dp)
        TabooToastVariant.CIRCLE -> RoundedCornerShape(percent = 100)
    }

    @Composable
    fun backgroundColor() = if (isSystemInDarkTheme()) TabooGray800 else Color.White

    @Composable
    fun textColor() = if (isSystemInDarkTheme()) Color.White else TabooGray700
}


@ThemePreviews
@Composable
private fun TabooToastPreviews1() {
    TabooTheme {
        TabooBackground {
            val bottomToast = remember { TabooToastState() }
            val topToast = remember { TabooToastState() }
            val coroutine = rememberCoroutineScope()

            Column(modifier = Modifier.fillMaxSize()) {
                TabooButton(
                    onClick = {
                        coroutine.launch {
                            bottomToast.show(
                                newMessage = "토스트 메세지를 입력해주세요."
                            )
                        }
                    },
                    text = {
                        Text(text = "Show Bottom Slide Toast")
                    }
                )

                TabooButton(
                    onClick = {
                        coroutine.launch {
                            topToast.show(
                                newMessage = "토스트 메세지를 입력해주세요."
                            )
                        }
                    },
                    text = {
                        Text(text = "Show Top Slide Toast")
                    }
                )
            }

            TabooToast(
                state = topToast,
                text = "토스트 메세지를 입력해주세요.",
                position = TabooToastPosition.TOP
            )

            TabooToast(
                state = bottomToast,
                text = "토스트 메세지를 입력해주세요.",
                position = TabooToastPosition.BOTTOM,
                animateDuration = 500
            )
        }
    }
}

@ThemePreviews
@Composable
private fun TabooToastPreview2() {
    TabooTheme {
        TabooBackground {
            val toastState = remember { TabooToastState() }

            Column {
                TabooToast(
                    state = toastState,
                    text = "토스트 메세지를 입력해주세요."
                )

                TabooToast(
                    state = toastState,
                    variant = TabooToastVariant.CIRCLE,
                    text = "토스트 메세지를 입력해주세요."
                )

                TabooToast(
                    state = toastState,
                    text = "토스트 메세지를 입력해주세요.",
                    icon = painterResource(R.drawable.ic_important)
                )

                TabooToast(
                    state = toastState,
                    variant = TabooToastVariant.CIRCLE,
                    text = "토스트 메세지를 입력해주세요.",
                    icon = painterResource(R.drawable.ic_important)
                )
            }
        }
    }
}