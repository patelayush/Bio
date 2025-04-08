package org.appsmith.bio

import App
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.backgroundDark

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(Color.Black.toArgb(), Color.Black.toArgb()),
            navigationBarStyle = SystemBarStyle.dark(backgroundDark.toArgb())
        )
        super.onCreate(savedInstanceState)
        val dynamicThemingAvailable = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
        setContent {
            App(dynamicThemingAvailable)
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App(false)
}