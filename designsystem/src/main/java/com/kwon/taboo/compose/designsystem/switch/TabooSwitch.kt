package com.kwon.taboo.compose.designsystem.switch

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kwon.taboo.compose.designsystem.TabooBackground
import com.kwon.taboo.compose.designsystem.TabooShape
import com.kwon.taboo.compose.designsystem.ThemePreviews
import com.kwon.taboo.compose.designsystem.scaleClickable
import com.kwon.taboo.compose.designsystem.theme.TabooBlue200
import com.kwon.taboo.compose.designsystem.theme.TabooBlue600
import com.kwon.taboo.compose.designsystem.theme.TabooGray100
import com.kwon.taboo.compose.designsystem.theme.TabooGray200
import com.kwon.taboo.compose.designsystem.theme.TabooTheme
import com.kwon.taboo.compose.designsystem.theme.White

@Composable
fun TabooSwitch(
    content: @Composable (() -> Unit)? = null,
    isEnabled: Boolean = true,
    isChecked: Boolean = false
) {
    val interactionSource = remember { MutableInteractionSource() }

    var isChecked by remember { mutableStateOf(isChecked) }
    val offsetX by animateDpAsState(
        targetValue = if (isChecked) 10.dp else (-10).dp,
        animationSpec = tween()
    )

    Row(
        modifier = Modifier
            .padding(5.dp)
            .scaleClickable(
                onClick = { isChecked = !isChecked }
            )
    ) {

        if (content != null) {
            Box(
                modifier = Modifier.weight(1f)
            ) {
                content()
            }
        }


        Surface(
            modifier = Modifier
                .width(50.dp)
                .height(30.dp)
                .padding(3.dp)
                .background(
                    color = Color.Unspecified,
                    shape = TabooShape.ExtraLarge
                )
            ,
            color = TabooSwitchDefault.defaultContainerColor(
                isEnabled = isEnabled,
                isChecked = isChecked
            ),
            shape = TabooShape.ExtraLarge
        ) {
            Box(
                modifier = Modifier
                    .offset(offsetX)
                    .requiredSize(17.dp)
                    .clip(CircleShape)
                    .background(
                        color = TabooSwitchDefault.defaultBallColor(isEnabled)
                    )
            )
        }

    }
}


object TabooSwitchDefault {
    @Composable
    internal fun defaultContainerColor(isEnabled: Boolean, isChecked: Boolean): Color {
        return if (isEnabled) {
            if (isChecked) TabooBlue600 else TabooGray200
        } else {
            if (isChecked) TabooBlue200 else TabooGray100
        }
    }

    @Composable
    internal fun defaultBallColor(isEnabled: Boolean): Color {
        return if (isEnabled) White else TabooGray200
    }
}

@ThemePreviews
@Composable
private fun TabooSwitchPreview() {
    TabooTheme {
        TabooBackground {
            Column(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                TabooSwitch()
                TabooSwitch(
                    isChecked = true
                )

                TabooSwitch(
                    content = {
                        Text(text = "라디오 버튼1")
                    },
                    isChecked = true
                )
            }
        }
    }
}