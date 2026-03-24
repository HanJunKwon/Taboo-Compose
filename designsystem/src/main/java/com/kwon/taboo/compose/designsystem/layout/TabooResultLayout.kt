package com.kwon.taboo.compose.designsystem.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kwon.taboo.compose.designsystem.R
import com.kwon.taboo.compose.designsystem.TabooBackground
import com.kwon.taboo.compose.designsystem.ThemePreviews
import com.kwon.taboo.compose.designsystem.button.TabooButton
import com.kwon.taboo.compose.designsystem.theme.TabooBlack100
import com.kwon.taboo.compose.designsystem.theme.TabooBlack900
import com.kwon.taboo.compose.designsystem.theme.TabooFontFamily
import com.kwon.taboo.compose.designsystem.theme.TabooGray500
import com.kwon.taboo.compose.designsystem.theme.TabooGray700
import com.kwon.taboo.compose.designsystem.theme.TabooTheme

@Composable
fun TabooResultLayout(
    icon: Painter,
    title: String,
    description: String,
    button: (@Composable () -> Unit)? = null
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = icon,
            contentDescription = "",
            modifier = Modifier
                .size(48.dp)
        )

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = title,
            color = TabooResultLayoutDefaults.defaultTitleColor(),
            style = TextStyle(
                fontFamily = TabooFontFamily,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = description,
            color = TabooResultLayoutDefaults.defaultDescriptionColor(),
            style = TextStyle(
                fontFamily = TabooFontFamily,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            )
        )

        if (button != null) {
            Spacer(modifier = Modifier.height(12.dp))
            button()
        }
    }
}

object TabooResultLayoutDefaults {
    @Composable
    fun defaultTitleColor(): Color {
        return if (isSystemInDarkTheme()) {
            TabooBlack100
        } else {
            TabooBlack900
        }
    }

    @Composable
    fun defaultDescriptionColor(): Color {
        return if (isSystemInDarkTheme()) {
            TabooGray500
        } else {
            TabooGray700
        }
    }
}

@ThemePreviews
@Composable
private fun TabooResultLayoutPreview() {
    TabooTheme {
        TabooBackground {
            Column(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(50.dp)
            ) {
                TabooResultLayout(
                    icon = painterResource(
                        id = R.drawable.ic_file_empty
                    ),
                    title = "기안함",
                    description = "기안한 전자 결재가 없어요."
                )

                TabooResultLayout(
                    icon = painterResource(
                        id = R.drawable.ic_file_empty
                    ),
                    title = "기안함",
                    description = "기안한 전자 결재가 없어요.",
                    button = {
                        TabooButton(
                            onClick = {

                            },
                            text = {
                                Text(
                                    text = "새로고침"
                                )
                            }
                        )
                    }
                )
            }
        }
    }
}
