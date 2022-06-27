package com.example.myapplication.helper

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class UiText {
    data class DynamicString(val value: String) : UiText()
    class StringResources(
        @StringRes val resId: Int,
        vararg val args: Any
    ) : UiText()

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> value
            is StringResources -> stringResource(resId, *args)
        }
    }

    @Composable
    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> value
            is StringResources -> context.getString(resId, *args)
        }
    }
}
