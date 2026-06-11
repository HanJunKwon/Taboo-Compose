package com.kwon.taboo.compose.designsystem.layout

import androidx.compose.foundation.LocalOverscrollFactory
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kwon.taboo.compose.designsystem.TabooBackground
import com.kwon.taboo.compose.designsystem.ThemePreviews
import com.kwon.taboo.compose.designsystem.button.TabooButton
import com.kwon.taboo.compose.designsystem.dialog.TabooConfirmDefault
import com.kwon.taboo.compose.designsystem.theme.TabooFontFamily
import com.kwon.taboo.compose.designsystem.theme.TabooRed600
import com.kwon.taboo.compose.designsystem.theme.TabooTheme

@Composable
fun TabooDoubleBottomCTA(
    cancelText: String,
    onClickCancel: () -> Unit,
    confirmText: String,
    onClickConfirm: () -> Unit,
    modifier: Modifier = Modifier,
    isFill: Boolean = true,
    content: @Composable () -> Unit,
) {
    Column(modifier = modifier) {
        Box(modifier = Modifier.fillMaxWidth().weight(1f, isFill)) {
            content()
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            TabooButton(
                onClick = onClickCancel,
                modifier = Modifier.fillMaxWidth(0.5f),
                text = {
                    Text(
                        text = cancelText,
                        style = TextStyle(
                            fontFamily = TabooFontFamily,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                },
                colors = TabooConfirmDefault.cancelButtonColors()
            )

            TabooButton(
                onClick = onClickConfirm,
                modifier = Modifier.fillMaxWidth(1f),
                text = {
                    Text(
                        text = confirmText,
                        style = TextStyle(
                            fontFamily = TabooFontFamily,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            )
        }
    }
}

@ThemePreviews
@Composable
private fun TabooSingleBottomCTAPreviews() {
    TabooTheme {
        TabooBackground {
            TabooDoubleBottomCTA(
                cancelText = "취소",
                onClickCancel = {

                },
                confirmText = "확인",
                onClickConfirm = {

                }
            ) {
                CompositionLocalProvider(LocalOverscrollFactory provides null) {
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
}