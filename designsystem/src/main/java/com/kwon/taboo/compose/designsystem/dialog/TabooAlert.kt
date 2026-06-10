package com.kwon.taboo.compose.designsystem.dialog

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import com.kwon.taboo.compose.designsystem.theme.TabooBlack900
import com.kwon.taboo.compose.designsystem.theme.TabooFontFamily
import com.kwon.taboo.compose.designsystem.theme.TabooGray500
import com.kwon.taboo.compose.designsystem.theme.TabooTheme


@Composable
fun TabooAlert(
    onDismissRequest: () -> Unit,
    title: String,
    description: String? = null,
    buttonText: String
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

                TabooButton(
                    onClick = onDismissRequest,
                    modifier = Modifier.fillMaxWidth(),
                    text = {
                        Text(
                            text = buttonText
                        )
                    }
                )
            }
        }
    }
}

object TabooAlertDefaults {
    @Composable
    fun cardColors(): CardColors = CardDefaults.cardColors(
        containerColor = if (isSystemInDarkTheme()) TabooBlack900 else Color.White,
        contentColor = Color.Unspecified
    )

    @Composable
    fun titleColor(): Color {
        return if (isSystemInDarkTheme()) Color.White else Color.Black
    }

    @Composable
    fun descriptionColor(): Color {
        return TabooGray500
    }
}

@ThemePreviews
@Composable
private fun TabooAlertPreview() {
    TabooTheme {
        TabooBackground {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                var showDialog by remember { mutableStateOf(false) }
                Log.d(">>>", "Recomposition, showDialog = $showDialog")

                if (showDialog) {
                    TabooAlert(
                        onDismissRequest = {},
                        title = "타이틀",
                        description = "내용",
                        buttonText = "확인"
                    )
                }

                TabooButton(
                    modifier = Modifier.padding(top = 50.dp),
                    onClick = {
                        showDialog = true
                    },
                    text = {
                        Text(text = "Show Dialog")
                    }
                )
            }
        }
    }
}