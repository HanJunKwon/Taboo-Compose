package com.kwon.taboo.compose.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val TabooTypography = Typography(
    titleLarge = TextStyle(
        fontFamily = TabooFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    titleMedium = TextStyle(
        fontFamily = TabooFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = TabooFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = TabooFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp
    ),
    bodySmall = TextStyle(
        fontFamily = TabooFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp
    ),
)