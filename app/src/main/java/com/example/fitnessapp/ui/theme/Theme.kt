package com.example.fitnessapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat

private val LightColors = lightColorScheme(
    primary = GreenPrimary,               // Color principal (ej. fondo de botones principales)
    onPrimary = DarkGreyText,                // Color del contenido sobre el primario (ej. texto en botones principales)
    primaryContainer = PurpleGrey80,      // Un contenedor acentuado con el color primario
    onPrimaryContainer = Purple40,        // Contenido sobre primaryContainer
    secondary = DarkGreen,                // Color secundario (ej. para FABs, elementos destacados)
    onSecondary = WhiteText,              // Contenido sobre el secundario
    tertiary = Pink80,                    // Color terciario (para acentos menos prominentes)
    onTertiary = Pink40,                  // Contenido sobre el terciario
    background = LightGrey,               // Color de fondo de la mayoría de las pantallas
    onBackground = DarkGreyText,          // Color del contenido sobre el fondo
    surface = LightGrey,                  // Color de superficie de componentes como Card, Menu
    onSurface = DarkGreyText,             // Color del contenido sobre las superficies
    error = ErrorRed,                     // Color para indicar errores
    onError = WhiteText,                  // Contenido sobre el color de error
)

private val DarkColors = darkColorScheme(
    primary = GreenPrimary,               // Color principal (ej. fondo de botones principales)
    onPrimary = DarkGreyText,                // Color del contenido sobre el primario (ej. texto en botones principales)
    primaryContainer = PurpleGrey80,      // Un contenedor acentuado con el color primario
    onPrimaryContainer = Purple40,        // Contenido sobre primaryContainer
    secondary = DarkGreen,                // Color secundario (ej. para FABs, elementos destacados)
    onSecondary = WhiteText,              // Contenido sobre el secundario
    tertiary = Pink80,                    // Color terciario (para acentos menos prominentes)
    onTertiary = Pink40,                  // Contenido sobre el terciario
    background = LightGrey,               // Color de fondo de la mayoría de las pantallas
    onBackground = DarkGreyText,          // Color del contenido sobre el fondo
    surface = LightGrey,                  // Color de superficie de componentes como Card, Menu
    onSurface = DarkGreyText,             // Color del contenido sobre las superficies
    error = ErrorRed,                     // Color para indicar errores
    onError = WhiteText,                  // Contenido sobre el color de error
)

@Composable
fun FitnessAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColors
        else -> LightColors
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }
    val appShapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(8.dp),
        large = RoundedCornerShape(16.dp)
    )
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
        shapes = appShapes
    )
}