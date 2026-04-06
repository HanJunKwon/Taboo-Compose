package com.kwon.taboo.compose.designsystem.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.kwon.taboo.compose.designsystem.theme.TabooTheme

@Composable
fun BottomNavigation(
    content: @Composable RowScope.() -> Unit
) {
    Surface() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .defaultMinSize(
                    minHeight = 46.dp
                )
                .padding(vertical = 3.dp)
                .windowInsetsPadding(WindowInsets.navigationBars),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            content()
        }
    }
}

@ThemePreviews
@Composable
fun BottomNavigationPreviews() {
    TabooTheme() {
        TabooBackground {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
            ) {
                var selectedIndex by remember { mutableIntStateOf(0) }

                // 실제 구현할 때는 NavDisplay로 표현하는게 좋을 것 같음.
                Surface(modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)) {
                    when (selectedIndex) {
                        0 -> HomeScreenExample()
                        1 -> StarScreenExample()
                        2 -> FavoriteScreenExample()
                    }
                }

                BottomNavigation {
                    NavigationItem(
                        onClick = {
                            selectedIndex = 0
                        },
                        isSelected = selectedIndex == 0,
                        icon = painterResource(R.drawable.ic_home),
                        text = "Home"
                    )
                    NavigationItem(
                        onClick = {
                            selectedIndex = 1
                        },
                        isSelected = selectedIndex == 1,
                        icon = painterResource(R.drawable.ic_star),
                        text = "Star"
                    )
                    NavigationItem(
                        onClick = {
                            selectedIndex = 2
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
    Box() {
        Text(text = "HOME")
    }
}

@Composable
fun StarScreenExample() {
    Box() {
        Text(text = "STAR")
    }
}

@Composable
fun FavoriteScreenExample() {
    Box() {
        Text(text = "FAVORITE")
    }
}