package com.kwon.taboo.compose.designsystem.dialog

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.kwon.taboo.compose.designsystem.TabooBackground
import com.kwon.taboo.compose.designsystem.ThemePreviews
import com.kwon.taboo.compose.designsystem.button.TabooButton
import com.kwon.taboo.compose.designsystem.button.TabooButtonColors
import com.kwon.taboo.compose.designsystem.theme.TabooFontFamily
import com.kwon.taboo.compose.designsystem.theme.TabooGray100
import com.kwon.taboo.compose.designsystem.theme.TabooGray200
import com.kwon.taboo.compose.designsystem.theme.TabooGray300
import com.kwon.taboo.compose.designsystem.theme.TabooGray600
import com.kwon.taboo.compose.designsystem.theme.TabooOpacityWhiteW46
import com.kwon.taboo.compose.designsystem.theme.TabooTheme

@Composable
fun TabooConfirm(
    onDismissRequest: () -> Unit,
    onClickCancel: () -> Unit,
    onClickConfirm: () -> Unit,
    title: String,
    description: String? = null,
    confirmButton: String,
    cancelButton: String,
) {
    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            colors = TabooAlertDefaults.cardColors()
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = title,
                    style = TextStyle(
                        fontFamily = TabooFontFamily,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = TabooAlertDefaults.titleColor()
                )

                if (description != null) {
                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = description,
                        style = TextStyle(
                            fontFamily = TabooFontFamily,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = TabooAlertDefaults.descriptionColor()
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    TabooButton(
                        onClick = onClickCancel,
                        modifier = Modifier.fillMaxWidth(0.5f),
                        text = {
                            Text(
                                text = cancelButton,
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
                                text = confirmButton,
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
    }
}

object TabooConfirmDefault {

    @Composable
    fun cancelButtonColors() = TabooButtonColors(
        containerColor = if (isSystemInDarkTheme()) Color.Unspecified else TabooGray200,
        contentColor = if (isSystemInDarkTheme()) Color.White else TabooGray600,
        pressedContainerColor = if (isSystemInDarkTheme()) Color.Unspecified else TabooGray200,
        pressedContentColor = if (isSystemInDarkTheme()) Color.Unspecified else TabooGray600,
        disableContainerColor = if (isSystemInDarkTheme()) Color.Unspecified else TabooGray100,
        disableContentColor = if (isSystemInDarkTheme()) TabooOpacityWhiteW46 else TabooGray300
    )
}

@ThemePreviews
@Composable
private fun TabooConfirmPreviews() {
    TabooTheme {
        TabooBackground {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                TabooConfirm(
                    onDismissRequest = {},
                    onClickCancel = {},
                    onClickConfirm = {},
                    title = "Confirm 타이틀",
                    description = "내용",
                    confirmButton = "확인",
                    cancelButton = "취소"
                )
            }
        }
    }
}