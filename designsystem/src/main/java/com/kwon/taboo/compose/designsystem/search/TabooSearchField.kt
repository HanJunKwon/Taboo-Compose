package com.kwon.taboo.compose.designsystem.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kwon.taboo.compose.designsystem.R
import com.kwon.taboo.compose.designsystem.TabooBackground
import com.kwon.taboo.compose.designsystem.TabooShape
import com.kwon.taboo.compose.designsystem.ThemePreviews
import com.kwon.taboo.compose.designsystem.search.TabooSearchFieldDefaults.defaultBackgroundColor
import com.kwon.taboo.compose.designsystem.theme.TabooBlack700
import com.kwon.taboo.compose.designsystem.theme.TabooBlack900
import com.kwon.taboo.compose.designsystem.theme.TabooBlue600
import com.kwon.taboo.compose.designsystem.theme.TabooFontFamily
import com.kwon.taboo.compose.designsystem.theme.TabooGray200
import com.kwon.taboo.compose.designsystem.theme.TabooGray300
import com.kwon.taboo.compose.designsystem.theme.TabooGray400
import com.kwon.taboo.compose.designsystem.theme.TabooGray500
import com.kwon.taboo.compose.designsystem.theme.TabooGray600
import com.kwon.taboo.compose.designsystem.theme.TabooGray900
import com.kwon.taboo.compose.designsystem.theme.TabooTheme

@Composable
fun TabooSearchField(
    state: TextFieldState,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    BasicTextField(
        state = state,
        modifier = modifier
            .background(
                color = defaultBackgroundColor(enabled),
                shape = TabooShape.Medium
            )
            .widthIn(min = 60.dp)
            .padding(
                vertical = 10.dp,
                horizontal = 11.dp
            ),
        enabled = enabled,
        cursorBrush = TabooSearchFieldDefaults.CursorBrush,
        textStyle = TextStyle(
            fontSize = 14.sp,
            fontFamily = TabooFontFamily,
            fontWeight = FontWeight.Normal,
            color = TabooSearchFieldDefaults.defaultTextColor(enabled),
        ),
        lineLimits = TextFieldLineLimits.SingleLine,
        decorator = { innerTextFiled ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 검색 아이콘
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "",
                    tint = TabooSearchFieldDefaults.defaultIconColor(enabled)
                )

                Spacer(
                    modifier = Modifier.width(9.dp)
                )

                // 입력 영역
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    if (state.text.isEmpty()) {
                        Text(
                            text = "검색어를 입력해주세요.",
                            color = TabooSearchFieldDefaults.defaultPlaceholderColor(enabled),
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = TabooFontFamily,
                                fontWeight = FontWeight.Normal
                            )
                        )
                    }

                    innerTextFiled()
                }

                // 검색어 삭제 아이콘
                if (state.text.isNotEmpty()) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_textfield_clear),
                        contentDescription = "",
                        tint = TabooSearchFieldDefaults.defaultIconColor(enabled),
                        modifier = Modifier.clickable(
                            onClick = {
                                state.edit { replace(0, length, "") }
                            },
                            enabled = enabled
                        )
                    )
                }
            }
        }
    )
}

object TabooSearchFieldDefaults {

    @Composable
    fun defaultBackgroundColor(enabled: Boolean): Color {
        return if (isSystemInDarkTheme()) {
            if (enabled) TabooGray900 else TabooBlack900
        } else {
            TabooGray200
        }
    }

    @Composable
    fun defaultIconColor(enabled: Boolean): Color {
        return if (isSystemInDarkTheme()) {
            if (enabled) TabooGray500 else TabooBlack700
        } else {
            if (enabled) TabooGray600 else TabooGray300
        }
    }

    @Composable
    fun defaultTextColor(enabled: Boolean): Color {
        return if (isSystemInDarkTheme()) {
            if (enabled) Color.White else TabooBlack900
        } else {
            TabooBlack900
        }
    }

    @Composable
    fun defaultPlaceholderColor(enabled: Boolean): Color {
        return if (isSystemInDarkTheme()) {
            if (enabled) TabooGray500 else TabooBlack700
        } else {
            if (enabled) TabooGray500 else TabooGray300
        }
    }

    val CursorBrush = SolidColor(TabooBlue600)
}

@ThemePreviews
@Composable
private fun SearchFieldPreview() {
    TabooTheme {
        TabooBackground {
            Column(
                modifier = Modifier.padding(50.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                TabooSearchField(
                    state = TextFieldState()
                )

                TabooSearchField(
                    state = TextFieldState("권한준")
                )

                TabooSearchField(
                    state = TextFieldState(),
                    enabled = false
                )
            }
        }
    }
}