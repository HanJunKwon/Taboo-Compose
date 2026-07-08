package com.kwon.taboo.compose.designsystem.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kwon.taboo.compose.designsystem.R
import com.kwon.taboo.compose.designsystem.TabooBackground
import com.kwon.taboo.compose.designsystem.TabooShape
import com.kwon.taboo.compose.designsystem.ThemePreviews
import com.kwon.taboo.compose.designsystem.scalePointerInput
import com.kwon.taboo.compose.designsystem.switch.TabooSwitch
import com.kwon.taboo.compose.designsystem.theme.TabooBlack600
import com.kwon.taboo.compose.designsystem.theme.TabooBlack800
import com.kwon.taboo.compose.designsystem.theme.TabooBlue600
import com.kwon.taboo.compose.designsystem.theme.TabooGray100
import com.kwon.taboo.compose.designsystem.theme.TabooGray400
import com.kwon.taboo.compose.designsystem.theme.TabooGray600
import com.kwon.taboo.compose.designsystem.theme.TabooTheme
import com.kwon.taboo.compose.designsystem.theme.TabooTypography

object TabooListRow {
    @Composable
    operator fun invoke(
        modifier: Modifier = Modifier,
        onClick: () -> Unit,
        verticalPadding: VerticalPadding = VerticalPadding.MEDIUM,
        horizontalPadding: HorizontalPadding = HorizontalPadding.SMALL,
        left: Asset? = null,
        leftIconShape: IconShape = IconShape.ORIGINAL,
        header: (@Composable () -> Unit),
        description: (@Composable () -> Unit)? = null,
        right: (@Composable () -> Unit)? = null,
    ) {
        val interactionSource = remember { MutableInteractionSource() }
        val isPressed = interactionSource.collectIsPressedAsState().value

        Box(
            modifier = modifier
                .fillMaxWidth()
                .scalePointerInput(
                    onClick = onClick,
                    interactionSource = interactionSource
                )
                .background(
                    color = backgroundColor(isPressed),
                    shape = RoundedCornerShape(25)
                )
        ) {
            Row(
                modifier = Modifier
                        .padding(
                            vertical = getVerticalPaddingDp(verticalPadding),
                            horizontal = getHorizontalPaddingDp(horizontalPadding)
                        ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (left != null) {
                    Box(
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(36.dp)
                            .background(
                                color = if (leftIconShape == IconShape.ORIGINAL) Color.Transparent else TabooGray100,
                                shape = getShape(shape = leftIconShape)
                            )
                            .padding(3.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        when (left) {
                            is Asset.Icon -> {
                                Image(
                                    painter = painterResource(left.iconId),
                                    contentDescription = ""
                                )
                            }

                            is Asset.Text -> {
                                Text(
                                    text = left.text,
                                    style = left.textStyle,
                                    fontWeight = FontWeight.SemiBold,
                                    color = left.color
                                )
                            }
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .weight(1f)

                ) {
                    header()

                    if (description != null) {
                        description()
                    }
                }

                if (right != null) {
                    right()
                }
            }
        }
    }

    @Composable
    private fun backgroundColor(isPress: Boolean): Color {
        return if (isPress) {
            if (isSystemInDarkTheme()) {
                TabooBlack800
            } else {
                TabooGray100
            }
        } else {
            Color.Transparent
        }
    }

    private fun getVerticalPaddingDp(verticalPadding: VerticalPadding) : Dp {
        return when (verticalPadding) {
            VerticalPadding.SMALL -> 4.dp
            VerticalPadding.MEDIUM -> 8.dp
            VerticalPadding.LARGE -> 12.dp
            VerticalPadding.XLARGE -> 16.dp
        }
    }

    private fun getHorizontalPaddingDp(horizontalPadding: HorizontalPadding) : Dp {
        return when (horizontalPadding) {
            HorizontalPadding.XSMALL -> 4.dp
            HorizontalPadding.SMALL -> 10.dp
            HorizontalPadding.MEDIUM -> 15.dp
        }
    }

    private fun getShape(shape: IconShape) : Shape {
        return when (shape) {
            IconShape.ORIGINAL -> {
                TabooShape.Medium
            }

            IconShape.SQUIRCLE -> {
                TabooShape.Medium
            }

            IconShape.CIRCLE -> {
                CircleShape
            }
        }
    }

    sealed interface Asset {
        data class Icon(
            val iconId: Int,
            val shape: IconShape = IconShape.ORIGINAL,
        ): Asset

        data class Text(
            val text: String,
            val textStyle: TextStyle = TabooTypography.bodySmall,
            val color: Color = TabooBlue600,
            val shape: IconShape = IconShape.ORIGINAL,
        ): Asset
    }

    enum class IconShape {
        ORIGINAL,
        SQUIRCLE,
        CIRCLE
    }

    enum class VerticalPadding {
        SMALL,
        MEDIUM,
        LARGE,
        XLARGE
    }

    enum class HorizontalPadding {
        XSMALL,
        SMALL,
        MEDIUM
    }
}


@ThemePreviews
@Composable
fun TabooListRowPreviews() {
    TabooTheme() {
        TabooBackground {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                TabooListRow(
                    onClick = {},
                    left = TabooListRow.Asset.Icon(
                        iconId = R.drawable.icon_receipt_out_money_debit_withdrawal_withdraw
                    ),
                    header = {
                        Text(
                            text = "Header",
                            style = TabooTypography.titleMedium,
                        )
                    }
                )

                TabooListRow(
                    onClick = {},
                    left = TabooListRow.Asset.Icon(
                        iconId = R.drawable.icon_receipt_out_money_debit_withdrawal_withdraw
                    ),
                    leftIconShape = TabooListRow.IconShape.SQUIRCLE,
                    header = {
                        Text(
                            text = "Header",
                            style = TabooTypography.titleMedium,
                        )
                    }
                )

                TabooListRow(
                    onClick = {},
                    left = TabooListRow.Asset.Icon(
                        iconId = R.drawable.icon_receipt_out_money_debit_withdrawal_withdraw
                    ),
                    leftIconShape = TabooListRow.IconShape.CIRCLE,
                    header = {
                        Text(
                            text = "Header",
                            style = TabooTypography.titleMedium,
                        )
                    }
                )

                TabooListRow(
                    onClick = {},
                    left = TabooListRow.Asset.Icon(
                        iconId = R.drawable.icon_receipt_out_money_debit_withdrawal_withdraw
                    ),
                    header = {
                        Text(
                            text = "Header",
                            style = TabooTypography.titleMedium,
                        )
                    },
                    right = {
                        Text(
                            text = "대출",
                            style = TabooTypography.bodySmall,
                            color = TabooGray600
                        )
                    }
                )

                TabooListRow(
                    onClick = {

                    },
                    left = TabooListRow.Asset.Icon(
                        iconId = R.drawable.icon_receipt_out_money_debit_withdrawal_withdraw
                    ),
                    leftIconShape = TabooListRow.IconShape.SQUIRCLE,
                    header = {
                        Text(
                            text = "Header",
                            style = TabooTypography.titleMedium,
                        )
                    },
                    right = {
                        Image(
                            painter = painterResource(R.drawable.ic_chevron_right),
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(color = TabooGray400)
                        )
                    }
                )

                TabooListRow(
                    onClick = {},
                    left = TabooListRow.Asset.Icon(
                        iconId = R.drawable.icon_receipt_out_money_debit_withdrawal_withdraw
                    ),
                    leftIconShape = TabooListRow.IconShape.CIRCLE,
                    header = {
                        Text(
                            text = "Header",
                            style = TabooTypography.titleMedium,
                        )
                    },
                    right = {
                        TabooSwitch()
                    }
                )

                TabooListRow(
                    onClick = {},
                    left = TabooListRow.Asset.Icon(
                        iconId = R.drawable.icon_receipt_out_money_debit_withdrawal_withdraw
                    ),
                    header = {
                        Text(
                            text = "Header",
                            style = TabooTypography.titleMedium,
                        )
                    },
                    description = {
                        Text(
                            text = "description1\ndescription2",
                            style = TabooTypography.bodySmall,
                            color = TabooGray600
                        )
                    },
                    right = {
                        Text(
                            text = "대출",
                            style = TabooTypography.bodySmall,
                            color = TabooGray600
                        )
                    }
                )

                TabooListRow(
                    onClick = {},
                    left = TabooListRow.Asset.Icon(
                        iconId = R.drawable.icon_receipt_out_money_debit_withdrawal_withdraw
                    ),
                    leftIconShape = TabooListRow.IconShape.SQUIRCLE,
                    header = {
                        Text(
                            text = "Header",
                            style = TabooTypography.titleMedium,
                        )
                    },
                    description = {
                        Text(
                            text = "description1\ndescription2",
                            style = TabooTypography.bodySmall,
                            color = TabooGray600
                        )
                    },
                    right = {
                        Image(
                            painter = painterResource(R.drawable.ic_chevron_right),
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(color = TabooGray400)
                        )
                    }
                )

                TabooListRow(
                    onClick = {},
                    left = TabooListRow.Asset.Icon(
                        iconId = R.drawable.icon_receipt_out_money_debit_withdrawal_withdraw
                    ),
                    leftIconShape = TabooListRow.IconShape.CIRCLE,
                    header = {
                        Text(
                            text = "Header",
                            style = TabooTypography.titleMedium,
                        )
                    },
                    description = {
                        Text(
                            text = "description1",
                            style = TabooTypography.bodySmall,
                            color = TabooGray600
                        )
                        Text(
                            text = "description2",
                            style = TabooTypography.bodySmall,
                            color = TabooGray600
                        )
                        Text(
                            text = "description3",
                            style = TabooTypography.bodySmall,
                            color = TabooGray600
                        )
                    },
                    right = {
                        TabooSwitch()
                    }
                )

                TabooListRow(
                    onClick = {},
                    left = TabooListRow.Asset.Text(
                        text = "오늘"
                    ),
                    header = {
                        Text(
                            text = "Header",
                            style = TabooTypography.titleMedium,
                        )
                    }
                )

                TabooListRow(
                    onClick = {},
                    left = TabooListRow.Asset.Text(
                        text = "오늘"
                    ),
                    leftIconShape = TabooListRow.IconShape.SQUIRCLE,
                    header = {
                        Text(
                            text = "Header",
                            style = TabooTypography.titleMedium,
                        )
                    }
                )

                TabooListRow(
                    onClick = {},
                    verticalPadding = TabooListRow.VerticalPadding.LARGE,
                    horizontalPadding = TabooListRow.HorizontalPadding.MEDIUM,
                    left = TabooListRow.Asset.Text(
                        text = "12.31",
                        textStyle = TabooTypography.bodyLarge,
                        color = TabooBlack600
                    ),
                    leftIconShape = TabooListRow.IconShape.CIRCLE,
                    header = {
                        Text(
                            text = "Header",
                            style = TabooTypography.titleMedium,
                        )
                    }
                )
            }
        }
    }
}