package com.kwon.taboo.compose.designsystem.popup

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.kwon.taboo.compose.designsystem.TabooBackground
import com.kwon.taboo.compose.designsystem.TabooShape
import com.kwon.taboo.compose.designsystem.ThemePreviews
import com.kwon.taboo.compose.designsystem.button.TabooButton
import com.kwon.taboo.compose.designsystem.list.TabooListRow
import com.kwon.taboo.compose.designsystem.theme.TabooBlack800
import com.kwon.taboo.compose.designsystem.theme.TabooFontFamily
import com.kwon.taboo.compose.designsystem.theme.TabooGray800
import com.kwon.taboo.compose.designsystem.theme.TabooTheme

@Composable
fun TabooPopupMenu(
    state: TabooPopupMenuState,
    colors: TabooPopupMenuColors = TabooPopupMenuDefault.getDefaultColors(),
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit
) {
    val shadowClipPadding = 25.dp

    Popup(
        onDismissRequest = onDismissRequest
    ) {
        AnimatedVisibility(
            visible = state.isVisible,
            modifier = Modifier
                .offset(
                    x = -shadowClipPadding,
                    y = -shadowClipPadding
                )
                .background(
                    color = Color.Transparent
                )
        ) {
            Column(
                modifier = Modifier
                    .widthIn(max = 250.dp)
                    .padding(all = shadowClipPadding)
                    .dropShadow(
                        shape = TabooShape.Medium,
                        shadow = Shadow(
                            radius = 20.dp,
                            spread = 0.dp,
                            color = Color(0xFF191F28).copy(alpha = 0.12f),
                            offset = DpOffset(x = 0.dp, y = 6.dp)
                        )
                    )
                    .dropShadow(
                        shape = TabooShape.Medium,
                        shadow = Shadow(
                            radius = 5.dp,
                            spread = 0.dp,
                            color = Color(0xFF191F28).copy(alpha = 0.04f),
                            offset = DpOffset(x = 0.dp, y = 2.dp)
                        )
                    )
                    .background(
                        color = colors.backgroundColor,
                        shape = TabooShape.Medium
                    )
            ) {
                content()
            }
        }
    }

}

object TabooPopupMenuDefault {
    @Composable
    fun getDefaultColors() = TabooPopupMenuColors(
        backgroundColor = getBackgroundColor()
    )

    @Composable
    private fun getBackgroundColor(): Color = if(isSystemInDarkTheme()) TabooBlack800 else Color.White
}

@ThemePreviews
@Composable
fun TabooPopupMenuPreviews() {
    val popupMenuState = remember { TabooPopupMenuState() }

    TabooTheme() {
        TabooBackground {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                TabooButton(
                    onClick = { popupMenuState.show() },
                    text = {
                        Text(text = "팝업 열기")
                    }
                )
            }
            TabooPopupMenu(
                state = popupMenuState,
                onDismissRequest = {
                    popupMenuState.dismiss()
                }
            ) {
                TabooListRow(
                    onClick = { popupMenuState.dismiss() },
                    header = {
                        Text(
                            text = "아이템 1",
                            style = TextStyle(
                                fontFamily = TabooFontFamily,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = TabooGray800
                            )
                        )
                    }
                )

                TabooListRow(
                    onClick = { popupMenuState.dismiss() },
                    header = {
                        Text(
                            text = "아이템 2",
                            style = TextStyle(
                                fontFamily = TabooFontFamily,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = TabooGray800
                            )
                        )
                    }
                )

                TabooListRow(
                    onClick = { popupMenuState.dismiss() },
                    header = {
                        Text(
                            text = "아이템 3",
                            style = TextStyle(
                                fontFamily = TabooFontFamily,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = TabooGray800
                            )
                        )
                    }
                )
            }
        }
    }
}