package com.kwon.taboo.compose.designsystem.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import com.kwon.taboo.compose.designsystem.TabooBackground
import com.kwon.taboo.compose.designsystem.ThemePreviews
import com.kwon.taboo.compose.designsystem.button.TabooButton
import com.kwon.taboo.compose.designsystem.list.TabooListRow
import com.kwon.taboo.compose.designsystem.theme.TabooBlack900
import com.kwon.taboo.compose.designsystem.theme.TabooTheme

@Composable
fun TabooBottomDialog(
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onDismissRequest
                )
                .background(Color.Transparent),
            contentAlignment = Alignment.BottomCenter
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 5.dp),
                shape = RoundedCornerShape(25.dp),
                colors = TabooBottomDialogDefaults.cardColors()
            ) {
                content()
            }
        }
    }
}

object TabooBottomDialogDefaults {
    @Composable
    fun cardColors(): CardColors = CardDefaults.cardColors(
        containerColor = if (isSystemInDarkTheme()) TabooBlack900 else Color.White,
        contentColor = Color.Unspecified
    )
}


@ThemePreviews
@Composable
fun TabooBottomDialogPreviews() {
    TabooTheme() {
        TabooBackground {
            var showBottomDialog by remember { mutableStateOf(false) }

            if (showBottomDialog) {
                TabooBottomDialog(
                    onDismissRequest = {
                        showBottomDialog = false
                    }
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        TabooListRow(
                            onClick = {},
                            header = {
                                Text("아이템1")
                            }
                        )

                        TabooListRow(
                            onClick = {},
                            header = {
                                Text("아이템2")
                            }
                        )

                        TabooListRow(
                            onClick = {},
                            header = {
                                Text("아이템2")
                            }
                        )

                        TabooListRow(
                            onClick = {},
                            header = {
                                Text("아이템2")
                            }
                        )

                        TabooListRow(
                            onClick = {},
                            header = {
                                Text("아이템2")
                            }
                        )
                    }
                }
            }

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                TabooButton(
                    onClick = {
                        showBottomDialog = true
                    },
                    text = {
                        Text(text = "Show Dialog")
                    }
                )
            }
        }
    }
}