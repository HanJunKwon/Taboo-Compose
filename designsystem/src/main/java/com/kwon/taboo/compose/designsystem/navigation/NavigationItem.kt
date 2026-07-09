package com.kwon.taboo.compose.designsystem.navigation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import com.kwon.taboo.compose.designsystem.scaleClickable
import com.kwon.taboo.compose.designsystem.theme.TabooBlack800
import com.kwon.taboo.compose.designsystem.theme.TabooBlack900
import com.kwon.taboo.compose.designsystem.theme.TabooFontFamily
import com.kwon.taboo.compose.designsystem.theme.TabooGray100
import com.kwon.taboo.compose.designsystem.theme.TabooGray300
import com.kwon.taboo.compose.designsystem.theme.TabooGray400
import com.kwon.taboo.compose.designsystem.theme.TabooGray600
import com.kwon.taboo.compose.designsystem.theme.TabooGray700
import com.kwon.taboo.compose.designsystem.theme.TabooGray800
import com.kwon.taboo.compose.designsystem.theme.TabooTheme

@Composable
fun RowScope.NavigationItem(
    onClick: () -> Unit,
    icon: Painter,
    text: String = "",
    colors: NavigationItemColors = NavigationItemDefault.colors(),
    isSelected: Boolean = false
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    Box(modifier = Modifier.weight(1f)) {
        Column(
            modifier = Modifier
                .scaleClickable(
                    onClick = onClick,
                    interactionSource = interactionSource
                )
                .widthIn(min = 46.dp)
                .background(
                    color = colors.backgroundColor(isPressed),
                    shape = RoundedCornerShape(25)
                )
                .padding(
                    vertical = 2.dp,
                    horizontal = 5.dp
                )
                .align(Alignment.Center)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Image(
                painter = icon,
                contentDescription = "",
                modifier = Modifier
                    .width(24.dp),
                colorFilter = ColorFilter.tint(color = colors.iconColor(isSelected))
            )

            Text(
                text = text,
                style = TextStyle(
                    fontFamily = TabooFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 10.sp,
                    color = colors.textColor(isSelected)
                )
            )
        }
    }
}

object NavigationItemDefault {
    @Composable
    fun colors(): NavigationItemColors {
        return if (isSystemInDarkTheme()) {
            NavigationItemColors(
                backgroundColor = Color.Unspecified,
                pressBackgroundColor = TabooGray800,
                selectedColor = TabooGray300,
                unselectedColor = TabooGray700
            )
        } else {
            NavigationItemColors(
                backgroundColor = Color.Unspecified,
                pressBackgroundColor = TabooGray100,
                selectedColor = TabooBlack800,
                unselectedColor = TabooGray400
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
                    icon = painterResource(R.drawable.ic_favorite),
                    text = "즐겨찾기"
                )
            }
        }
    }
}