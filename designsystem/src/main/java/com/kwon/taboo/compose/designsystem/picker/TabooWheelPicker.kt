package com.kwon.taboo.compose.designsystem.picker

import android.util.Log
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kwon.taboo.compose.designsystem.TabooBackground
import com.kwon.taboo.compose.designsystem.ThemePreviews
import com.kwon.taboo.compose.designsystem.theme.TabooBlack
import com.kwon.taboo.compose.designsystem.theme.TabooFontFamily
import com.kwon.taboo.compose.designsystem.theme.TabooGray300
import com.kwon.taboo.compose.designsystem.theme.TabooGray400
import com.kwon.taboo.compose.designsystem.theme.TabooGray500
import com.kwon.taboo.compose.designsystem.theme.TabooGray600
import com.kwon.taboo.compose.designsystem.theme.TabooGray700
import com.kwon.taboo.compose.designsystem.theme.TabooGray800
import com.kwon.taboo.compose.designsystem.theme.TabooTheme
import com.kwon.taboo.compose.designsystem.theme.White

@Composable
fun TabooWheelPicker(
    modifier: Modifier = Modifier,
    colors: TabooWheelPickerColor = TabooWheelPickerDefaults.pickerColors(),
    textStyle: TextStyle = TabooWheelPickerDefaults.pickerTextStyle(),
    enabled: Boolean = true,
    displayItemCount: Int = 3,
    itemHeight: Dp = 30.dp,
    contents: List<String> = listOf(),
    initialFirstVisibleItemIndex: Int = 0,
    onItemSelected: (Int) -> Unit
) {
    val lazyListState = rememberLazyListState(
        initialFirstVisibleItemIndex = initialFirstVisibleItemIndex
    )
    val snapFlingBehavior = rememberSnapFlingBehavior(lazyListState)
    val selectedIndex by remember {
        derivedStateOf {
            val layoutInfo = lazyListState.layoutInfo
            if (layoutInfo.visibleItemsInfo.isEmpty()) {
                0
            } else {
                val center = (layoutInfo.viewportStartOffset + layoutInfo.viewportEndOffset) / 2

                layoutInfo.visibleItemsInfo.minByOrNull {
                    kotlin.math.abs((it.offset + it.size / 2) - center)
                }?.index ?: 0
            }
        }
    }

    LaunchedEffect(selectedIndex) {
        onItemSelected(selectedIndex)
    }

    Box(modifier = modifier) {
        LazyColumn(
            modifier = Modifier
                .height(itemHeight * displayItemCount)
                .wrapContentWidth()
            ,
            userScrollEnabled = enabled,
            state = lazyListState,
            flingBehavior = snapFlingBehavior,
            contentPadding = PaddingValues(vertical = itemHeight * (displayItemCount / 2))
        ) {
            itemsIndexed(items = contents, key = { _, content -> content }) { index, content ->
                Text(
                    modifier = Modifier.height(itemHeight),
                    text = content,
                    color = if (index == selectedIndex) {
                        if (enabled) {
                            colors.selectedTextColor
                        } else {
                            colors.disabledSelectedTextColor
                        }
                    } else {
                        if (enabled) {
                            colors.textColor
                        } else {
                            colors.disabledTextColor
                        }
                    },
                    style = textStyle
                )
            }
        }
    }
}

object TabooWheelPickerDefaults {
    @Composable
    fun pickerColors() = TabooWheelPickerColor(
        textColor = defaultTextColor(),
        selectedTextColor = defaultSelectedTextColor(),
        disabledTextColor = defaultDisabledTextColor(),
        disabledSelectedTextColor = defaultDisabledSelectedTextColor()
    )

    @Composable
    private fun defaultTextColor() = if (isSystemInDarkTheme()) TabooGray600 else TabooGray500

    @Composable
    private fun defaultSelectedTextColor() = if (isSystemInDarkTheme()) White else TabooBlack

    @Composable
    private fun defaultDisabledTextColor() = if (isSystemInDarkTheme()) TabooGray600 else TabooGray500

    @Composable
    private fun defaultDisabledSelectedTextColor() = if (isSystemInDarkTheme()) White else TabooBlack

    fun pickerTextStyle() = TextStyle(
        fontSize = 14.sp,
        fontFamily = TabooFontFamily,
        fontWeight = FontWeight.Medium
    )
}

@ThemePreviews
@Composable
fun TabooWheelPickerPreviews() {
    TabooTheme {
        TabooBackground {
            Surface(modifier = Modifier.fillMaxSize().statusBarsPadding()) {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    TabooWheelPicker(
                        modifier = Modifier,
                        displayItemCount = 5,
                        initialFirstVisibleItemIndex = 1,
                        contents = listOf("2000년","2001년","2002년","2003년","2004년","2005년","2006년","2007년","2008년","2009년","2010년","2011년")
                    ) { index ->
                        Log.d(">>>", "selected index = $index")
                    }

                    TabooWheelPicker(
                        modifier = Modifier,
                        displayItemCount = 5,
                        contents = listOf("1월","2월","3월","4월","6월","7월","8월","9월","10월","11월","12월")
                    ) { index ->
                        Log.d(">>>", "selected index = $index")
                    }

                    TabooWheelPicker(
                        modifier = Modifier,
                        enabled = false,
                        displayItemCount = 5,
                        contents = listOf("1일", "2일", "3일", "4일", "5일", "6일")
                    ) { index ->
                        Log.d(">>>", "selected index = $index")
                    }
                }
            }
        }
    }
}