package com.kwon.taboo.compose.designsystem.switch

import androidx.compose.ui.graphics.Color
import com.kwon.taboo.compose.designsystem.theme.TabooBlue200
import com.kwon.taboo.compose.designsystem.theme.TabooBlue600
import com.kwon.taboo.compose.designsystem.theme.TabooGray100
import com.kwon.taboo.compose.designsystem.theme.TabooGray200

class TabooSwitchColors(
    val checkedContainerColor: Color,
    val unCheckedContainerColor: Color,
    val enabledContainerColor: Color,
    val disabledContainerColor: Color,
    val enabledBallColor: Color,
    val disabledBallColor: Color
) {
    fun containerColor(isEnabled: Boolean, isChecked: Boolean): Color {
        return if (isEnabled) {
            if (isChecked) TabooBlue600 else TabooGray200
        } else {
            if (isChecked) TabooBlue200 else TabooGray100
        }
    }
}