package com.kwon.taboo.compose.designsystem.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kwon.taboo.compose.designsystem.R
import com.kwon.taboo.compose.designsystem.TabooBackground
import com.kwon.taboo.compose.designsystem.ThemePreviews
import com.kwon.taboo.compose.designsystem.button.TabooIconButton
import com.kwon.taboo.compose.designsystem.theme.TabooBlack900
import com.kwon.taboo.compose.designsystem.theme.TabooOpacityWhiteW46
import com.kwon.taboo.compose.designsystem.theme.TabooTheme
import com.kwon.taboo.compose.designsystem.theme.White

@Composable
fun TabooNavigationBar(
    modifier: Modifier = Modifier,
    onBackButton: (() -> Unit)? = null,
    title: @Composable (() -> Unit)? = null,
    right: @Composable (() -> Unit)? = null,
    options: NavigationBarOption = NavigationBarDefaults.options()
) {
    if (options.isTransparentBackground) {
        modifier.background(
            color = Color.Transparent
        )
    }

    Box(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth().heightIn(min = 48.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (options.withBackButton) {
                TabooIconButton(
                    onClick = {
                        onBackButton?.invoke()
                    },
                    icon = painterResource(R.drawable.icon_arrow_back_ios_mono),
                    iconColorFilter = ColorFilter.tint(
                        color = NavigationBarDefaults.backButtonColor()
                    )
                )
            }

            if (title != null) {
                title()
            }

            if (right != null) {
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.End
                ) {
                    right()
                }
            }
        }
    }
}

object NavigationBarDefaults {
    fun options() = NavigationBarOption(
        withBackButton = true,
        isTransparentBackground = true
    )

    @Composable
    fun backButtonColor() =
        if (isSystemInDarkTheme()) TabooOpacityWhiteW46
        else TabooBlack900
}

data class NavigationBarOption(
    val withBackButton: Boolean,
    val isTransparentBackground: Boolean
)

@ThemePreviews
@Composable
fun TabooNavigationBarPreviews() {
    TabooTheme() {
        TabooBackground {
            Box(modifier = Modifier.fillMaxSize()) {

                Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                    TabooNavigationBar()

                    TabooNavigationBar(
                        title = {
                            Row {
                                Image(
                                    painter = painterResource(R.drawable.icon_receipt_out_money_debit_withdrawal_withdraw),
                                    contentDescription = ""
                                )
                                Text(
                                    text = "영수증",
                                    color = if (isSystemInDarkTheme()) White else TabooBlack900
                                )
                            }
                        }
                    )

                    TabooNavigationBar(
                        title = {
                            Row {
                                Image(
                                    painter = painterResource(R.drawable.icon_receipt_out_money_debit_withdrawal_withdraw),
                                    contentDescription = ""
                                )
                                Text(
                                    text = "영수증",
                                    color = if (isSystemInDarkTheme()) White else TabooBlack900
                                )
                            }
                        },
                        right = {
                            TabooIconButton(
                                onClick = {},
                                icon = painterResource(R.drawable.ic_home)
                            )
                        }
                    )
                }
            }
        }
    }
}