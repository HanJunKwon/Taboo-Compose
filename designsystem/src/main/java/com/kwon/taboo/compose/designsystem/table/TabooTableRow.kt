package com.kwon.taboo.compose.designsystem.table

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kwon.taboo.compose.designsystem.TabooBackground
import com.kwon.taboo.compose.designsystem.ThemePreviews
import com.kwon.taboo.compose.designsystem.theme.TabooBlack700
import com.kwon.taboo.compose.designsystem.theme.TabooBlack900
import com.kwon.taboo.compose.designsystem.theme.TabooFontFamily
import com.kwon.taboo.compose.designsystem.theme.TabooGray500
import com.kwon.taboo.compose.designsystem.theme.TabooGray600
import com.kwon.taboo.compose.designsystem.theme.TabooGray700
import com.kwon.taboo.compose.designsystem.theme.TabooTheme

@Composable
fun TabooTableRow(
    left: String,
    right: String,
    rightTextAlign: TextAlign = TextAlign.End
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 5.dp,
                horizontal = 5.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = left,
            color = TabooTableRowDefaults.defaultLeftTextColor(),
            style = TextStyle(
                fontFamily = TabooFontFamily,
                fontWeight = FontWeight.Normal
            )
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = right,
            color = TabooTableRowDefaults.defaultRightTextColor(),
            modifier = Modifier.fillMaxWidth(1f),
            textAlign = rightTextAlign,
            style = TextStyle(
                fontFamily = TabooFontFamily,
                fontWeight = FontWeight.Normal
            )
        )
    }
}

object TabooTableRowDefaults {
    @Composable
    fun defaultLeftTextColor() = if (isSystemInDarkTheme()) TabooGray500 else TabooBlack700

    @Composable
    fun defaultRightTextColor() = if (isSystemInDarkTheme()) TabooGray500 else TabooBlack700
}

@ThemePreviews
@Composable
fun TabooTableRowPreview() {
    TabooTheme {
        TabooBackground {
            Column() {
                TabooTableRow(
                    left = "기안일",
                    right = "2026.01.05"
                )

                TabooTableRow(
                    left = "기안일",
                    right = "2026.01.05"
                )

                TabooTableRow(
                    left = "기안일",
                    right = "2026.01.05",
                    rightTextAlign = TextAlign.Center
                )

                TabooTableRow(
                    left = "기안일",
                    right = "2026.01.05",
                    rightTextAlign = TextAlign.Start
                )
            }
        }
    }
}