package com.kwon.taboo.compose.designsystem.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kwon.taboo.compose.designsystem.TabooBackground
import com.kwon.taboo.compose.designsystem.ThemePreviews
import com.kwon.taboo.compose.designsystem.scaleClickable
import com.kwon.taboo.compose.designsystem.theme.TabooBlack700
import com.kwon.taboo.compose.designsystem.theme.TabooBlack900
import com.kwon.taboo.compose.designsystem.theme.TabooFontFamily
import com.kwon.taboo.compose.designsystem.theme.TabooGray100
import com.kwon.taboo.compose.designsystem.theme.TabooGray400
import com.kwon.taboo.compose.designsystem.theme.TabooGray500
import com.kwon.taboo.compose.designsystem.theme.TabooTheme

@Composable
fun Chip(
    onClick: () -> Unit,
    isSelected: Boolean = false,
    colors: ChipColors = ChipDefault.defaultChipColors(),
    label: String
) {
    Box(
        modifier = Modifier
            .scaleClickable(
                onClick = onClick
            )
            .background(
                color = colors.backgroundColor(isSelected),
                shape = RoundedCornerShape(25)
            )
            .padding(
                vertical = 5.dp,
                horizontal = 10.dp
            )
    ) {
        Text(
            text = label,
            color = colors.textColor(isSelected),
            style = TextStyle(
                fontFamily = TabooFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
        )
    }
}

object ChipDefault {
    @Composable
    fun defaultChipColors() = ChipColors(
        textColor = textColor(),
        selectedTextColor = selectedTextColor(),
        backgroundColor = Color.Transparent,
        selectedBackgroundColor = selectedBackgroundColor()
    )

    @Composable
    private fun textColor() = if (isSystemInDarkTheme()) TabooGray500 else TabooGray400

    @Composable
    private fun selectedTextColor() = if (isSystemInDarkTheme()) TabooGray500 else TabooBlack700

    @Composable
    private fun selectedBackgroundColor() = if (isSystemInDarkTheme()) TabooBlack900 else TabooGray100
}

@ThemePreviews
@Composable
fun ChipPreviews() {
    TabooTheme() {
        TabooBackground {
            Row(
                modifier = Modifier.fillMaxSize()
            ) {

                Chip(
                    onClick = {},
                    label = "Chip",
                    isSelected = true
                )

                Chip(
                    onClick = {},
                    label = "Chip"
                )
            }
        }
    }
}