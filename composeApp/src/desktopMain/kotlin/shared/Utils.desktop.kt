package shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalWindowInfo
import model.Message
import java.awt.Desktop
import java.net.URI
import java.util.Locale

actual object AppContext

actual fun platform(): String {
    return "desktop"
}

@Composable
actual fun PdfColumn(url: String, modifier: Modifier) {
    val uri = URI.create(url)
    val osName by lazy(LazyThreadSafetyMode.NONE) {
        System.getProperty("os.name").lowercase(Locale.getDefault())
    }
    val desktop = Desktop.getDesktop()
    when {
        Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE) -> desktop.browse(
            uri
        )

        "mac" in osName -> Runtime.getRuntime().exec("open $uri")
        "nix" in osName || "nux" in osName -> Runtime.getRuntime().exec("xdg-open $uri")
        else -> throw RuntimeException("cannot open $uri")
    }
}

actual suspend fun sendEmail(message: Message) {
    TODO("Not yet implemented")
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun isTabletVersion(): Boolean {
    val windowInfo = LocalWindowInfo.current
    val screenWidth = windowInfo.containerSize.width
    println("screenWidth desktop: $screenWidth")
    return true
}