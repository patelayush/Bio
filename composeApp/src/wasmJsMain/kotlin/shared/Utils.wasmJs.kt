package shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalWindowInfo
import email
import kotlinx.browser.window
import model.Message
import org.w3c.dom.url.URLSearchParams

actual object AppContext

actual fun platform(): String {
    return "web"
}

@Composable
actual fun PdfColumn(url: String, modifier: Modifier) {
    window.open(url)
}

actual suspend fun sendEmail(message: Message) {
    try {
        val params = URLSearchParams()
        params.append("to", email)
        params.append("subject", message.getSubject().replace(" ", "%20"))
        params.append("body", message.body ?: "")

        val mailtoLink = "mailto:?${params}"
        window.location.href = mailtoLink
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun isTabletVersion(): Boolean {
    val windowInfo = LocalWindowInfo.current
    val screenWidth = windowInfo.containerSize.width
    return screenWidth > 1300
}