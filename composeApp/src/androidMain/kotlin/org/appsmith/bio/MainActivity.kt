package org.appsmith.bio

import App
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dynamicThemingAvailable = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
        setContent {
            val window = this.window
            val decorView = window.decorView
            WindowCompat.getInsetsController(window, decorView).isAppearanceLightStatusBars =
                !isSystemInDarkTheme()
            App(dynamicThemingAvailable)
        }

    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App(false)
}