package com.kwon.taboo.compose.designsystem.checkbox

import android.widget.Space
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kwon.taboo.compose.designsystem.R
import com.kwon.taboo.compose.designsystem.TabooBackground
import com.kwon.taboo.compose.designsystem.ThemePreviews
import com.kwon.taboo.compose.designsystem.indication.ScaleIndication
import com.kwon.taboo.compose.designsystem.scaleClickable
import com.kwon.taboo.compose.designsystem.theme.TabooBlack900
import com.kwon.taboo.compose.designsystem.theme.TabooBlue600
import com.kwon.taboo.compose.designsystem.theme.TabooFontFamily
import com.kwon.taboo.compose.designsystem.theme.TabooGray200
import com.kwon.taboo.compose.designsystem.theme.TabooGray300
import com.kwon.taboo.compose.designsystem.theme.TabooTheme
import com.kwon.taboo.compose.designsystem.theme.White

@Composable
fun TabooCheckBox(
    content: @Composable (() -> Unit)? = null,
    contentColor: Color = TabooCheckBoxDefaults.defaultContentColor(),
    variant: TabooCheckBoxVariant = TabooCheckBoxVariant.CIRCLE,
    isChecked: Boolean = false
) {
    var isChecked by remember { mutableStateOf(isChecked) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .scaleClickable(
                onClick = {
                    isChecked = !isChecked
                }
            )
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (content != null) {
            Surface(
                modifier = Modifier.weight(1f),
                color = Color.Transparent,
                contentColor = contentColor
            ) {
                content()
            }

            Spacer(
                modifier = Modifier.width(8.dp)
            )
        }

        when (variant) {
            TabooCheckBoxVariant.CIRCLE -> TabooCheckBoxCircle(
                variant = variant,
                isChecked = isChecked
            )
            TabooCheckBoxVariant.LINE -> TabooCheckBoxLine(
                variant = variant,
                isChecked = isChecked
            )
        }
    }
}

@Composable
private fun TabooCheckBoxCircle(
    variant: TabooCheckBoxVariant,
    isChecked: Boolean
) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(
                color = TabooCheckBoxDefaults.defaultCheckIconBackgroundColor(isChecked)
            )
            .padding(6.dp)
    ) {
        Icon(
            modifier = Modifier.wrapContentWidth().wrapContentHeight(),
            painter = painterResource(id = R.drawable.ic_check),
            contentDescription = "",
            tint = TabooCheckBoxDefaults.defaultCheckIconColor(
                variant = variant,
                isChecked = isChecked
            )
        )
    }
}

@Composable
fun TabooCheckBoxLine(
    variant: TabooCheckBoxVariant,
    isChecked: Boolean
) {
    Icon(
        modifier = Modifier.wrapContentWidth().wrapContentHeight(),
        painter = painterResource(id = R.drawable.ic_check),
        contentDescription = "",
        tint = TabooCheckBoxDefaults.defaultCheckIconColor(variant, isChecked)
    )
}

object TabooCheckBoxDefaults {
    @Composable
    internal fun defaultContentColor(): Color {
        return if (isSystemInDarkTheme()) TabooGray200 else TabooBlack900
    }

    @Composable
    internal fun defaultCheckIconBackgroundColor(isChecked: Boolean): Color {
        return if (isChecked) TabooBlue600 else Color.Transparent
    }

    @Composable
    internal fun defaultCheckIconColor(variant: TabooCheckBoxVariant, isChecked: Boolean): Color {
        return when (variant) {
            TabooCheckBoxVariant.CIRCLE -> {
                if (isChecked) White else TabooGray300
            }
            TabooCheckBoxVariant.LINE -> {
                if (isChecked) TabooBlue600 else TabooGray300
            }
        }
    }
}

enum class TabooCheckBoxVariant {
    CIRCLE,
    LINE
}

@ThemePreviews
@Composable
private fun TabooCheckBoxCirclePreview() {
    TabooTheme {
        TabooBackground {
            Column {
                TabooCheckBox()

                TabooCheckBox(isChecked = true)

                TabooCheckBox(
                    content = {
                        Text(
                            text = "선택 항목1",
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                )

                TabooCheckBox(
                    content = {
                        Text(
                            text = "선택 항목1",
                            modifier = Modifier.weight(1f)
                        )
                    },
                    isChecked = true
                )

                TabooCheckBox(
                    content = {
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(3.dp)
                        ) {
                            Text(
                                text= "제목",
                                style = TextStyle(
                                    fontFamily = TabooFontFamily,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                            Text(
                                text = "내용",
                                style = TextStyle(
                                    fontFamily = TabooFontFamily,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            )
                        }
                    }
                )

                TabooCheckBox(
                    content = {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(3.dp)
                        ) {
                            Text(text= "제목")
                            Text(
                                text = "내용",
                                style = TextStyle(
                                    fontFamily = TabooFontFamily,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            )
                        }
                    },
                    isChecked = true
                )
            }
        }
    }
}


@ThemePreviews
@Composable
private fun TabooCheckBoxLinePreview() {
    TabooTheme {
        TabooBackground {
            Column {
                TabooCheckBox(
                    variant = TabooCheckBoxVariant.LINE
                )

                TabooCheckBox(
                    variant = TabooCheckBoxVariant.LINE,
                    isChecked = true
                )

                TabooCheckBox(
                    content = {
                        Text(
                            text = "선택 항목1",
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    variant = TabooCheckBoxVariant.LINE
                )

                TabooCheckBox(
                    content = {
                        Text(
                            text = "선택 항목1",
                            modifier = Modifier.weight(1f)
                        )
                    },
                    variant = TabooCheckBoxVariant.LINE,
                    isChecked = true
                )

                TabooCheckBox(
                    content = {
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(3.dp)
                        ) {
                            Text(
                                text= "제목",
                                style = TextStyle(
                                    fontFamily = TabooFontFamily,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                            Text(
                                text = "내용",
                                style = TextStyle(
                                    fontFamily = TabooFontFamily,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            )
                        }
                    },
                    variant = TabooCheckBoxVariant.LINE
                )

                TabooCheckBox(
                    content = {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(3.dp)
                        ) {
                            Text(text= "제목")
                            Text(
                                text = "내용",
                                style = TextStyle(
                                    fontFamily = TabooFontFamily,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            )
                        }
                    },
                    variant = TabooCheckBoxVariant.LINE,
                    isChecked = true
                )
            }
        }
    }
}