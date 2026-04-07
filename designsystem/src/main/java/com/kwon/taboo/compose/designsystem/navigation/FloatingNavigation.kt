package com.kwon.taboo.compose.designsystem.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kwon.taboo.compose.designsystem.R
import com.kwon.taboo.compose.designsystem.TabooBackground
import com.kwon.taboo.compose.designsystem.ThemePreviews
import com.kwon.taboo.compose.designsystem.theme.TabooBlack900
import com.kwon.taboo.compose.designsystem.theme.TabooGray100
import com.kwon.taboo.compose.designsystem.theme.TabooGray400
import com.kwon.taboo.compose.designsystem.theme.TabooGray800
import com.kwon.taboo.compose.designsystem.theme.TabooTheme

@Composable
fun FloatingNavigation(
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    iconBackgroundColor: Color = FloatingNavigationDefault.iconBackgroundColor(),
    iconColorFilter: ColorFilter = FloatingNavigationDefault.iconColorFilter(),
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        modifier = modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .padding(10.dp)
            .windowInsetsPadding(WindowInsets.navigationBars)
        ,
        color = FloatingNavigationDefault.backgroundColor(),
        shape = RoundedCornerShape(100),
        shadowElevation = 3.dp
    ) {
        Row(
            modifier = Modifier.wrapContentWidth()
                .background(color = FloatingNavigationDefault.backgroundColor()),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (icon != null) {
                Image(
                    painter = icon,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .background(
                            color = iconBackgroundColor,
                            shape = RoundedCornerShape(100)
                        )
                        .padding(5.dp),
                    colorFilter = iconColorFilter
                )
            }

            Row(
                modifier = Modifier.padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                content()
            }
        }
    }
}

object FloatingNavigationDefault {
    @Composable
    fun iconBackgroundColor(): Color {
        return if (isSystemInDarkTheme()) TabooGray800 else TabooGray100
    }

    @Composable
    fun iconColorFilter(): ColorFilter {
        return ColorFilter.tint(color = TabooGray400)
    }

    @Composable
    fun backgroundColor(): Color {
        return if (isSystemInDarkTheme()) TabooBlack900 else Color.White
    }
}

@ThemePreviews
@Composable
fun FloatingNavigationPreviews() {
    TabooTheme {
        TabooBackground {
            Box(
                modifier = Modifier.fillMaxSize().statusBarsPadding()
            ) {
                var selectedIndex by remember { mutableIntStateOf(0) }

                Surface(modifier = Modifier.fillMaxSize()) {
                    when (selectedIndex) {
                        0 -> HomeScreenExample()
                        1 -> StarScreenExample()
                        2 -> FavoriteScreenExample()
                    }
                }

                FloatingNavigation(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    icon = painterResource(R.drawable.ic_arrow_back),
                ) {
                    NavigationItem(
                        onClick = {
                            selectedIndex = 0
                        },
                        icon = painterResource(R.drawable.ic_home),
                        text = "홈",
                        isSelected = selectedIndex == 0
                    )

                    NavigationItem(
                        onClick = {
                            selectedIndex = 1
                        },
                        icon = painterResource(R.drawable.ic_star),
                        text = "추천",
                        isSelected = selectedIndex == 1
                    )

                    NavigationItem(
                        onClick = {
                            selectedIndex = 2
                        },
                        icon = painterResource(R.drawable.ic_favorite),
                        text = "즐겨찾기",
                        isSelected = selectedIndex == 2
                    )
                }
            }
        }
    }
}