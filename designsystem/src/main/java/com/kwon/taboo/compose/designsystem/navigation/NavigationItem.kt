package com.kwon.taboo.compose.designsystem.navigation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.kwon.taboo.compose.designsystem.scaleClickable
import com.kwon.taboo.compose.designsystem.theme.TabooFontFamily
import com.kwon.taboo.compose.designsystem.theme.TabooGray300
import com.kwon.taboo.compose.designsystem.theme.TabooGray600
import com.kwon.taboo.compose.designsystem.theme.TabooGray700
import com.kwon.taboo.compose.designsystem.theme.TabooTheme

@Composable
fun NavigationItem(
    onClick: () -> Unit,
    icon: Painter,
    text: String,
    colors: NavigationItemColors = NavigationItemDefault.colors(),
    isSelected: Boolean = false,
    isSubNavigation: Boolean = false
) {
    Column(
        modifier = Modifier
            .padding(5.dp)
            .scaleClickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Image(
            painter = icon,
            contentDescription = "",
            colorFilter = ColorFilter.tint(color = colors.iconColor(isSelected))
        )

        Text(
            text = text,
            style = TextStyle(
                fontFamily = TabooFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = colors.textColor(isSelected)
            )
        )
    }
}

object NavigationItemDefault {
    @Composable
    fun colors(): NavigationItemColors {
        return if (isSystemInDarkTheme()) {
            NavigationItemColors(
                selectedColor = TabooGray300,
                unselectedColor = TabooGray700
            )
        } else {
            NavigationItemColors(
                selectedColor = TabooGray600,
                unselectedColor = TabooGray300
            )
        }
    }
}

@ThemePreviews
@Composable
fun NavigationItemPreviews() {
    TabooTheme {
        TabooBackground {
            Row {
                NavigationItem(
                    onClick = {
                        Log.d(">>> NavigationItem", "")
                    },
                    icon = painterResource(R.drawable.ic_home),
                    text = "홈",
                    isSelected = true
                )

                NavigationItem(
                    onClick = {
                        Log.d(">>> NavigationItem", "")
                    },
                    icon = painterResource(R.drawable.ic_home),
                    text = "홈"
                )
            }
        }
    }
}