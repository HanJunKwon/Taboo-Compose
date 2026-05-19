package com.kwon.taboo.compose.designsystem.segment

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kwon.taboo.compose.designsystem.TabooBackground
import com.kwon.taboo.compose.designsystem.TabooShape
import com.kwon.taboo.compose.designsystem.ThemePreviews
import com.kwon.taboo.compose.designsystem.scaleClickable
import com.kwon.taboo.compose.designsystem.theme.TabooBlack800
import com.kwon.taboo.compose.designsystem.theme.TabooBlack900
import com.kwon.taboo.compose.designsystem.theme.TabooFontFamily
import com.kwon.taboo.compose.designsystem.theme.TabooGray100
import com.kwon.taboo.compose.designsystem.theme.TabooGray500
import com.kwon.taboo.compose.designsystem.theme.TabooGray700
import com.kwon.taboo.compose.designsystem.theme.TabooTheme

@Composable
fun TabooSegmentTab(
    modifier: Modifier = Modifier,
    labels: List<String>,
    textStyle: TextStyle = TabooSegmentControlDefaults.defaultTextStyle(),
    segmentTabColors: TabooSegmentTabColors = TabooSegmentControlDefaults.defaultSegmentColors()
) {
    var selectedIndex by remember { mutableIntStateOf(0) }

    val localDensity = LocalDensity.current
    var itemWidthDp by remember { mutableStateOf(0.dp) }
    var itemHeightDp by remember { mutableStateOf(0.dp) }

    val offsetX by animateDpAsState(
        targetValue = itemWidthDp * selectedIndex,
        animationSpec = spring()
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = segmentTabColors.containerColor,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(3.dp)
    ) {
        // Selector Box
        // Todo: selector 색상 또는 그라데이션 등 부가 옵션 추가
        Surface(
            modifier = Modifier
                .width(itemWidthDp)
                .height(itemHeightDp)
                .offset(x = offsetX, y = 0.dp)
            ,
            color = segmentTabColors.selectorColor,
            shape = TabooShape.Medium
        ) {}

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    val heightPixel = coordinates.size.height
                    itemHeightDp = with(localDensity) { heightPixel.toDp() }
                }
        ) {
            itemWidthDp = maxWidth / labels.size

            LazyRow(
                modifier = Modifier.wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                itemsIndexed(labels) { index, item ->
                    Text(
                        text = item,
                        modifier = Modifier
                            .width(itemWidthDp)
                            .padding(
                                vertical = 10.dp,
                                horizontal = 10.dp
                            )
                            .scaleClickable(
                                onClick = { selectedIndex = index }
                            )
                        ,
                        style = textStyle,
                        textAlign = TextAlign.Center,
                        color = if (selectedIndex == index) segmentTabColors.selectedTextColor else segmentTabColors.unselectedTextColor
                    )
                }
            }
        }
    }
}

/**
 * [TabooSegmentTab]의 기본 속성들을 관리하는 클래스.
 */
object TabooSegmentControlDefaults {
    @Composable
    fun defaultTextStyle() = TextStyle(
        fontSize = 16.sp,
        fontFamily = TabooFontFamily,
        fontWeight = FontWeight.Normal
    )

    @Composable
    fun defaultSegmentColors() = TabooSegmentTabColors(
        containerColor = if (isSystemInDarkTheme()) TabooBlack900 else TabooGray100,
        selectedTextColor = if (isSystemInDarkTheme()) Color.White else TabooGray700,
        unselectedTextColor = if (isSystemInDarkTheme()) Color.White else TabooGray500,
        selectorColor = if (isSystemInDarkTheme()) TabooBlack800 else Color.White
    )

}

@ThemePreviews
@Composable
private fun TabooSegmentTabPreview() {
    TabooTheme {
        TabooBackground {
            Column(
                modifier = Modifier.padding(10.dp).height(100.dp)
            ) {
                TabooSegmentTab(
                    modifier = Modifier.fillMaxWidth(),
                    labels = listOf("Item 1", "아이템 2")
                )
            }
        }
    }
}