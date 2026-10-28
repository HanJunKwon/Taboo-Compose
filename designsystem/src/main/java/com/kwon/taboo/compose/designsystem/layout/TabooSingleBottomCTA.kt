package com.kwon.taboo.compose.designsystem.layout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kwon.taboo.compose.designsystem.TabooBackground
import com.kwon.taboo.compose.designsystem.ThemePreviews
import com.kwon.taboo.compose.designsystem.button.TabooButton
import com.kwon.taboo.compose.designsystem.theme.TabooFontFamily
import com.kwon.taboo.compose.designsystem.theme.TabooRed600
import com.kwon.taboo.compose.designsystem.theme.TabooTheme
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun TabooSingleBottomCTA(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    isFill: Boolean = true,
    buttonVisible: Boolean = true,
    buttonEnabled: Boolean = true,
    content: @Composable () -> Unit
) {
    val density = LocalDensity.current

    Column(modifier = modifier) {
        Box(modifier = Modifier.fillMaxWidth().weight(1f, isFill)) {
            content()
        }

        AnimatedVisibility(
            visible = buttonVisible,
            enter = slideInVertically {
                with(density) { -40.dp.roundToPx() }
            } + expandVertically(
                expandFrom = Alignment.Bottom
            ) + fadeIn(
                initialAlpha = 0.3f
            ),
            exit = slideOutVertically() + shrinkVertically() + fadeOut()
        ) {
            TabooButton(
                onClick = onClick,
                text = {
                    Text(
                        text = text,
                        style = TextStyle(
                            fontFamily = TabooFontFamily,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                enabled = !isLoading && buttonEnabled,
                isLoading = isLoading
            )
        }
    }
}

@ThemePreviews
@Composable
private fun TabooSingleBottomCTAPreviews() {
    TabooTheme {
        TabooBackground {
            var visible by remember { mutableStateOf(false) }

            LaunchedEffect(Unit) {
                while (true) {
                    delay(2_000.milliseconds)
                    visible = visible.not()
                }
            }

            TabooSingleBottomCTA(
                text = "확인",
                onClick = {

                },
                buttonEnabled = true,
                isLoading = true,
                modifier = Modifier.fillMaxWidth().fillMaxHeight()
            ) {
                LazyColumn(
                    modifier = Modifier.padding(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(30) {
                        Box(
                            modifier = Modifier.fillMaxWidth().height(50.dp).background(color = TabooRed600)
                        )
                    }
                }
            }
        }
    }
}