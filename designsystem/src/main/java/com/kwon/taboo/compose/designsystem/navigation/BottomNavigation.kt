package com.kwon.taboo.compose.designsystem.navigation

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kwon.taboo.compose.designsystem.R
import com.kwon.taboo.compose.designsystem.TabooBackground
import com.kwon.taboo.compose.designsystem.ThemePreviews
import com.kwon.taboo.compose.designsystem.theme.TabooBlack900
import com.kwon.taboo.compose.designsystem.theme.TabooOpacityBlackB11
import com.kwon.taboo.compose.designsystem.theme.TabooOpacityGrayG10
import com.kwon.taboo.compose.designsystem.theme.TabooOpacityGrayG5
import com.kwon.taboo.compose.designsystem.theme.TabooOpacityWhiteW5
import com.kwon.taboo.compose.designsystem.theme.TabooRed600
import com.kwon.taboo.compose.designsystem.theme.TabooTheme
import com.kwon.taboo.compose.designsystem.theme.White

@Composable
fun BottomNavigation(
    modifier: Modifier = Modifier,
    isSubNavigation: Boolean = false,
    subNavigation: (@Composable RowScope.() -> Unit)? = null,
    onBack: (() -> Unit)? = null,
    content: @Composable RowScope.() -> Unit
) {
    val animatedMargin by animateDpAsState(
        if (isSubNavigation) 10.dp else 0.dp
    )

    val animatedTopRounded by animateDpAsState(
        targetValue = if (isSubNavigation) 32.dp else 16.dp
    )

    val animatedBottomRounded by animateDpAsState(
        targetValue = if (isSubNavigation) 32.dp else 0.dp
    )

    val animatedBottomPadding by animateDpAsState(
        targetValue = if (isSubNavigation) 5.dp else 10.dp
    )

    val shape = RoundedCornerShape(
        topStart = animatedTopRounded,
        topEnd = animatedTopRounded,
        bottomStart = animatedBottomRounded,
        bottomEnd = animatedBottomRounded
    )

    val backgroundColor = if (isSubNavigation) Color.Unspecified else BottomNavigationDefault.backgroundColor()

    Box(
        modifier = modifier
            .padding(animatedMargin)
            .background(
                color = backgroundColor,
                shape = shape
            )
            .border(
                width = 1.dp,
                color = BottomNavigationDefault.borderColor(),
                shape = shape
            )
            .windowInsetsPadding(WindowInsets.navigationBars),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(
                    top = 5.dp,
                    bottom = animatedBottomPadding
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (isSubNavigation) {
                NavigationRow(
                    backIcon = painterResource(R.drawable.ic_arrow_back),
                    onBack = {
                        onBack?.invoke()
                    },
                    iconBackgroundColor = NavigationRowDefault.iconBackgroundColor(),
                    iconColor = NavigationRowDefault.iconColor(),
                ) {
                    if (subNavigation != null) {
                        subNavigation()
                    }
                }
            } else {
                content()
            }
        }
    }
}

object BottomNavigationDefault {
    @Composable
    fun backgroundColor(): Color {
        return if (isSystemInDarkTheme()) TabooBlack900 else Color.White
    }

    @Composable
    fun borderColor(): Color {
        return if (isSystemInDarkTheme()) TabooOpacityWhiteW5 else TabooOpacityGrayG5
    }
}

@ThemePreviews
@Composable
fun BottomNavigationPreviews() {
    TabooTheme() {
        TabooBackground {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
            ) {
                var selectedIndex by remember { mutableIntStateOf(0) }
                var isSubNavigation by remember { mutableStateOf(false) }
                var subNavigationContent by remember {
                    mutableStateOf<@Composable RowScope.() -> Unit>({ })
                }


                // 실제 구현할 때는 NavDisplay로 표현하는게 좋을 것 같음.
                Surface(modifier = Modifier
                    .fillMaxSize()
                ) {
                    when (selectedIndex) {
                        0 -> HomeScreenExample()
                        1 -> StarScreenExample()
                        2 -> FavoriteScreenExample()
                    }
                }

                BottomNavigation(
                    isSubNavigation = isSubNavigation,
                    subNavigation = subNavigationContent,
                    onBack = {
                        isSubNavigation = false
                    },
                    modifier = Modifier.align(Alignment.BottomCenter)
                ) {
                    NavigationItem(
                        onClick = {
                            selectedIndex = 0
                            isSubNavigation = false
                        },
                        isSelected = selectedIndex == 0,
                        icon = painterResource(R.drawable.ic_home),
                        text = "Home"
                    )
                    NavigationItem(
                        onClick = {
                            selectedIndex = 1
                            isSubNavigation = true
                            subNavigationContent = {
                                NavigationItem(
                                    onClick = {

                                    },
                                    icon = painterResource(R.drawable.ic_star),
                                    text = "즐겨찾기 1"
                                )

                                NavigationItem(
                                    onClick = {

                                    },
                                    icon = painterResource(R.drawable.ic_star),
                                    text = "즐겨찾기 2"
                                )

                                NavigationItem(
                                    onClick = {

                                    },
                                    icon = painterResource(R.drawable.ic_star),
                                    text = "즐겨찾기 3"
                                )
                            }
                        },
                        isSelected = selectedIndex == 1,
                        icon = painterResource(R.drawable.ic_star),
                        text = "Star"
                    )
                    NavigationItem(
                        onClick = {
                            selectedIndex = 2
                            isSubNavigation = false
                        },
                        isSelected = selectedIndex == 2,
                        icon = painterResource(R.drawable.ic_favorite),
                        text = "Favorite"
                    )
                }
            }
        }
    }
}

@Composable
fun HomeScreenExample() {
    Box(
        modifier = Modifier.background(color = if (isSystemInDarkTheme()) TabooBlack900 else White)
    ) {
        Text(text = "HOME")
    }
}

@Composable
fun StarScreenExample() {
    Box(
        modifier = Modifier.background(color = White)
    ) {
        Text(text = "STAR")
    }
}

@Composable
fun FavoriteScreenExample() {
    Box(
        modifier = Modifier.background(color = White)
    ) {
        Text(text = "FAVORITE")
    }
}