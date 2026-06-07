package com.kwon.taboo.compose.designsystem.textfield

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kwon.taboo.compose.designsystem.R
import com.kwon.taboo.compose.designsystem.TabooBackground
import com.kwon.taboo.compose.designsystem.ThemePreviews
import com.kwon.taboo.compose.designsystem.theme.TabooBlack600
import com.kwon.taboo.compose.designsystem.theme.TabooBlack900
import com.kwon.taboo.compose.designsystem.theme.TabooBlue600
import com.kwon.taboo.compose.designsystem.theme.TabooFontFamily
import com.kwon.taboo.compose.designsystem.theme.TabooGray100
import com.kwon.taboo.compose.designsystem.theme.TabooGray300
import com.kwon.taboo.compose.designsystem.theme.TabooGray400
import com.kwon.taboo.compose.designsystem.theme.TabooGray600
import com.kwon.taboo.compose.designsystem.theme.TabooGray700
import com.kwon.taboo.compose.designsystem.theme.TabooGray800
import com.kwon.taboo.compose.designsystem.theme.TabooGray900
import com.kwon.taboo.compose.designsystem.theme.TabooTheme


@Composable
fun TabooTextField(
    state: TextFieldState,
    variant: TabooTextFieldVariant = TabooTextFieldVariant.BOX,
    title: String,
    placeHolder: String? = null,
    colors: TabooTextFieldColors = TabooTextFieldDefaults.colors(),
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    isPassword: Boolean = false,
    showPassword: Boolean = false,
) {
    when (variant) {
        TabooTextFieldVariant.BOX -> TabooBoxTextField(
            state,
            title,
            placeHolder,
            colors,
            enabled,
            keyboardOptions,
            isPassword,
            showPassword
        )
        TabooTextFieldVariant.LINE -> TabooLineTextField(
            state,
            title,
            placeHolder,
            colors,
            enabled,
            keyboardOptions,
            isPassword,
            showPassword
        )
    }
}

@Composable
fun TabooBoxTextField(
    state: TextFieldState,
    title: String,
    placeHolder: String?,
    colors: TabooTextFieldColors,
    enabled: Boolean,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    isPassword: Boolean,
    showPassword: Boolean
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused = interactionSource.collectIsFocusedAsState().value
    var showPassword by remember { mutableStateOf(showPassword) }

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        Text(
            text = title,
            color = colors.titleColor(enabled, isFocused),
            style = TabooTextFieldDefaults.titleTextStyle()
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colors.contentColor,
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(15.dp)
        ) {
            if (isPassword) {
                BasicSecureTextField(
                    state = state,
                    textObfuscationMode = if (showPassword) {
                        TextObfuscationMode.Visible
                    } else {
                        TextObfuscationMode.Hidden
                    },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = keyboardOptions,
                    decorator = { innerTextField ->
                        if (state.text.isEmpty()) {

                            if (placeHolder != null) {
                                Text(
                                    text = placeHolder,
                                    color = colors.placeHolderColor,
                                    style = TabooTextFieldDefaults.placeHolderTextStyle(variant = TabooTextFieldVariant.BOX)
                                )
                            }

                        } else {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Box(modifier = Modifier.weight(1f)) {
                                    innerTextField()
                                }
                                Image(
                                    painter = painterResource(id = R.drawable.ic_visibility_24dp),
                                    modifier = Modifier
                                        .padding(start = 10.dp)
                                        .clickable(
                                            enabled = enabled,
                                            onClick = {
                                                showPassword = !showPassword
                                            }
                                        ),
                                    colorFilter = ColorFilter.tint(
                                        color = if (showPassword) {
                                            colors.showPasswordColor
                                        } else {
                                            colors.hidePasswordColor
                                        }
                                    ),
                                    contentDescription = ""
                                )
                            }
                        }
                    },
                    textStyle = TabooTextFieldDefaults.textFieldTextStyle(variant = TabooTextFieldVariant.BOX).copy(
                        color = colors.textColor(enabled, isFocused)
                    ),
                    enabled = enabled,
                    interactionSource = interactionSource
                )
            } else {
                BasicTextField(
                    state = state,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = keyboardOptions,
                    decorator = { innerTextField ->
                        if (state.text.isEmpty()) {

                            if (placeHolder != null) {
                                Text(
                                    text = placeHolder,
                                    color = colors.placeHolderColor,
                                    style = TabooTextFieldDefaults.placeHolderTextStyle(variant = TabooTextFieldVariant.BOX)
                                )
                            }

                        } else {
                            innerTextField()
                        }
                    },
                    textStyle = TabooTextFieldDefaults.textFieldTextStyle(variant = TabooTextFieldVariant.BOX).copy(
                        color = colors.textColor(enabled, isFocused)
                    ),
                    lineLimits = TextFieldLineLimits.SingleLine,
                    enabled = enabled,
                    interactionSource = interactionSource
                )
            }
        }
    }
}

@Composable
fun TabooLineTextField(
    state: TextFieldState,
    title: String,
    placeHolder: String?,
    colors: TabooTextFieldColors = TabooTextFieldDefaults.colors(),
    enabled: Boolean,
    keyboardOptions: KeyboardOptions,
    isPassword: Boolean,
    showPassword: Boolean
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused = interactionSource.collectIsFocusedAsState().value
    var showPassword by remember { mutableStateOf(showPassword) }

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = title,
            color = colors.titleColor(enabled, isFocused),
            style = TabooTextFieldDefaults.titleTextStyle()
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .drawBehind {
                    drawLine(
                        color = TabooBlack600,
                        start = Offset(x = 0f, y = size.height),
                        end = Offset(x = size.width, y = size.height),
                        strokeWidth = 2f,
                        colorFilter = ColorFilter.tint(color = colors.lineColor(enabled, isFocused))
                    )
                }
                .padding(
                    top = 7.dp,
                    bottom = 9.dp
                )
        ) {
            if (isPassword) {
                BasicSecureTextField(
                    state = state,
                    textObfuscationMode = if (showPassword) {
                        TextObfuscationMode.Visible
                    } else {
                        TextObfuscationMode.Hidden
                    },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 7.dp),
                    decorator = { innerTextField ->
                        if (state.text.isEmpty()) {

                            if (placeHolder != null) {
                                Text(
                                    text = placeHolder,
                                    color = colors.placeHolderColor,
                                    style = TabooTextFieldDefaults.placeHolderTextStyle(variant = TabooTextFieldVariant.LINE)
                                )
                            }

                        } else {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Box(modifier = Modifier.weight(1f)) {
                                    innerTextField()
                                }

                                Image(
                                    painter = painterResource(id = R.drawable.ic_visibility_24dp),
                                    modifier = Modifier
                                        .padding(start = 10.dp)
                                        .clickable(
                                            enabled = enabled,
                                            onClick = {
                                                showPassword = !showPassword
                                            }
                                        ),
                                    colorFilter = ColorFilter.tint(
                                        color = if (showPassword) {
                                            colors.showPasswordColor
                                        } else {
                                            colors.hidePasswordColor
                                        }
                                    ),
                                    contentDescription = ""
                                )
                            }
                        }
                    },
                    textStyle = TabooTextFieldDefaults.textFieldTextStyle(variant = TabooTextFieldVariant.LINE).copy(
                        color = colors.textColor(enabled, isFocused)
                    ),
                    enabled = enabled,
                    interactionSource = interactionSource
                )
            } else {
                BasicTextField(
                    state = state,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 7.dp),
                    keyboardOptions = keyboardOptions,
                    decorator = { innerTextField ->
                        if (state.text.isEmpty()) {

                            if (placeHolder != null) {
                                Text(
                                    text = placeHolder,
                                    color = colors.placeHolderColor,
                                    style = TabooTextFieldDefaults.placeHolderTextStyle(variant = TabooTextFieldVariant.LINE)
                                )
                            }

                        } else {
                            innerTextField()
                        }
                    },
                    textStyle = TabooTextFieldDefaults.textFieldTextStyle(variant = TabooTextFieldVariant.LINE).copy(
                        color = colors.textColor(enabled, isFocused)
                    ),
                    lineLimits = TextFieldLineLimits.SingleLine,
                    enabled = enabled,
                    interactionSource = interactionSource
                )
            }
        }
    }
}

enum class TabooTextFieldVariant {
    BOX,
    LINE
}

object TabooTextFieldDefaults {

    @Composable
    fun titleTextStyle(): TextStyle = TextStyle(
        fontFamily = TabooFontFamily,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal
    )

    @Composable
    fun placeHolderTextStyle(variant: TabooTextFieldVariant): TextStyle {
        return when (variant) {
            TabooTextFieldVariant.BOX -> {
                TextStyle(
                    fontFamily = TabooFontFamily,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            TabooTextFieldVariant.LINE -> {
                TextStyle(
                    fontFamily = TabooFontFamily,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }

    @Composable
    fun textFieldTextStyle(variant: TabooTextFieldVariant): TextStyle {
        return when (variant) {
            TabooTextFieldVariant.BOX -> {
                TextStyle(
                    fontFamily = TabooFontFamily,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            TabooTextFieldVariant.LINE -> {
                TextStyle(
                    fontFamily = TabooFontFamily,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }

    @Composable
    fun colors() = TabooTextFieldColors(
        titleColor = defaultTitleColor(),
        focusedTitleColor = defaultFocusedTitleColor(),
        disabledTitleColor = defaultDisabledTitleColor(),
        textColor = defaultTextColor(),
        focusedTextColor = defaultFocusedTextColor(),
        disabledTextColor = defaultDisabledTextColor(),
        placeHolderColor = defaultPlaceHolderColor(),
        contentColor = defaultContentColor(),
        disabledContentColor = defaultDisabledContentColor(),
        lineColor = defaultLineColor(),
        focusedLineColor = defaultFocusedLineColor(),
        disabledLineColor = defaultDisabledLineColor(),
        showPasswordColor = defaultShowPasswordColor(),
        hidePasswordColor = defaultHidePasswordColor()
    )

    @Composable
    private fun defaultTitleColor(): Color = if (isSystemInDarkTheme()) Color.White else TabooBlack900

    @Composable
    private fun defaultFocusedTitleColor(): Color = if (isSystemInDarkTheme()) TabooBlue600 else TabooBlue600

    @Composable
    private fun defaultDisabledTitleColor(): Color = if (isSystemInDarkTheme()) TabooGray600 else TabooGray600

    @Composable
    private fun defaultTextColor(): Color = if (isSystemInDarkTheme()) Color.White else TabooBlack900

    @Composable
    private fun defaultFocusedTextColor(): Color = if (isSystemInDarkTheme()) Color.White else TabooBlack900

    @Composable
    private fun defaultDisabledTextColor(): Color = if (isSystemInDarkTheme()) TabooGray900 else TabooGray600

    @Composable
    private fun defaultPlaceHolderColor(): Color = TabooGray400

    @Composable
    private fun defaultContentColor(): Color = if (isSystemInDarkTheme()) TabooGray800 else TabooGray100

    @Composable
    private fun defaultDisabledContentColor(): Color = if (isSystemInDarkTheme()) TabooGray800 else TabooGray100

    @Composable
    private fun defaultLineColor(): Color = TabooGray100

    @Composable
    private fun defaultFocusedLineColor(): Color = TabooBlue600

    @Composable
    private fun defaultDisabledLineColor(): Color = TabooGray100

    @Composable
    private fun defaultShowPasswordColor(): Color = if (isSystemInDarkTheme()) TabooGray600 else TabooGray700

    @Composable
    private fun defaultHidePasswordColor(): Color = if (isSystemInDarkTheme()) TabooGray900 else TabooGray300
}

@ThemePreviews
@Composable
fun TabooTextFieldVariantBoxPreviews() {
    TabooTheme {
        TabooBackground {
            Column(
                modifier = Modifier.padding(10.dp).fillMaxWidth().wrapContentHeight(),
                verticalArrangement = Arrangement.spacedBy(10.dp)

            ) {
                val textState = rememberTextFieldState(initialText = "")

                TabooTextField(
                    state = textState,
                    title = "타이틀",
                    placeHolder = "내용을 입력해주세요."
                )

                TabooTextField(
                    state = textState,
                    title = "비밀번호",
                    placeHolder = "비밀번호를 입력해주세요.",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    isPassword = true
                )

                TabooTextField(
                    state = textState,
                    title = "타이틀",
                    placeHolder = "내용을 입력해주세요.",
                    enabled = false
                )

                TabooTextField(
                    state = textState,
                    variant = TabooTextFieldVariant.LINE,
                    title = "타이틀",
                    placeHolder = "내용을 입력해주세요."
                )

                TabooTextField(
                    state = textState,
                    variant = TabooTextFieldVariant.LINE,
                    title = "비밀번호",
                    placeHolder = "비밀번호를 입력해주세요.",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    isPassword = true,
                )

                TabooTextField(
                    state = textState,
                    variant = TabooTextFieldVariant.LINE,
                    title = "타이틀",
                    placeHolder = "내용을 입력해주세요.",
                    enabled = false
                )
            }
        }
    }
}