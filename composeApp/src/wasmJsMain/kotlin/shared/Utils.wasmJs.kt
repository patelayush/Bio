package shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.browser.window

actual fun platform(): String {
    return "web"
}

@Composable
actual fun PdfColumn(url: String, modifier: Modifier) {
    window.open(url)
}