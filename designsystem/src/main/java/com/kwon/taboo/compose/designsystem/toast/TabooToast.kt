package com.kwon.taboo.compose.designsystem.toast

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.kwon.taboo.compose.designsystem.R
import com.kwon.taboo.compose.designsystem.TabooBackground
import com.kwon.taboo.compose.designsystem.ThemePreviews
import com.kwon.taboo.compose.designsystem.button.TabooButton
import com.kwon.taboo.compose.designsystem.theme.TabooBlack900
import com.kwon.taboo.compose.designsystem.theme.TabooFontFamily
import com.kwon.taboo.compose.designsystem.theme.TabooGray800
import com.kwon.taboo.compose.designsystem.theme.TabooTheme
import kotlinx.coroutines.delay

@Composable
fun TabooToast(
    variant: TabooToastVariant = TabooToastVariant.ROUNDED,
    icon: Painter? = null,
    text: String,
    duration: Long = 1_500L,
    isVisible: Boolean = false
) {

    AnimatedVisibility(
        visible = isVisible,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Transparent)
            .zIndex(100f),
        enter = slideInVertically(
            initialOffsetY = { -it },
            animationSpec = tween(durationMillis = TabooToastDefaults.animationDuration())
        ),
        exit = slideOutVertically(
            targetOffsetY = { -it },
            animationSpec = tween(durationMillis = TabooToastDefaults.animationDuration())
        )
    ) {
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .padding(16.dp)
                .shadow(
                    elevation = 5.dp,
                    shape = TabooToastDefaults.backgroundShape(variant)
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
    fun textColor() = if (isSystemInDarkTheme()) Color.White else TabooBlack900
}


@ThemePreviews
@Composable
private fun TabooToastPreviews1() {
    TabooTheme {
        TabooBackground {
            var isToastVisible by remember { mutableStateOf(false) }

            LaunchedEffect(isToastVisible) {
                if (isToastVisible) {
                    delay(1_500)
                    isToastVisible = false
                }
            }

            TabooToast(
                text = "토스트 메세지를 입력해주세요.",
                isVisible = isToastVisible
            )

            TabooButton(
                onClick = {
                    isToastVisible = true
                },
                text = {
                    Text(text = "Show Slide Toast")
                }
            )
        }
    }
}

@ThemePreviews
@Composable
private fun TabooToastPreview2() {
    TabooTheme {
        TabooBackground {
            Column {
                TabooToast(
                    text = "토스트 메세지를 입력해주세요.",
                    isVisible = true
                )

                TabooToast(
                    variant = TabooToastVariant.CIRCLE,
                    text = "토스트 메세지를 입력해주세요.",
                    isVisible = true
                )

                TabooToast(
                    text = "토스트 메세지를 입력해주세요.",
                    isVisible = true,
                    icon = painterResource(R.drawable.ic_important)
                )

                TabooToast(
                    variant = TabooToastVariant.CIRCLE,
                    text = "토스트 메세지를 입력해주세요.",
                    isVisible = true,
                    icon = painterResource(R.drawable.ic_important)
                )
            }
        }
    }
}