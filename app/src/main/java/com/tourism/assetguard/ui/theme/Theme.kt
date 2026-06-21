package com.tourism.assetguard.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = TealPrimary,
    secondary = CoralSecondary,
    background = SlateBackground,
    surface = SlateSurface,
    onPrimary = SlateBackground,
    onSecondary = SlateBackground,
    onBackground = SlateTextPrimary,
    onSurface = SlateTextPrimary
)

private val LightColorScheme = lightColorScheme(
    primary = TealPrimary,
    secondary = CoralSecondary,
    background = LightBackground,
    surface = LightSurface,
    onPrimary = LightSurface,
    onSecondary = LightSurface,
    onBackground = LightTextPrimary,
    onSurface = LightTextPrimary
)

@Composable
fun SmartTourismTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            var context = view.context
            while (context is android.content.ContextWrapper) {
                if (context is Activity) {
                    val window = context.window
                    window.statusBarColor = colorScheme.background.toArgb()
                    WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
                    break
                }
                context = context.baseContext
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // Added typography here
        content = content
    )
}
