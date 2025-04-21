package shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.interop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSURL
import platform.PDFKit.PDFDocument
import platform.PDFKit.PDFView

actual fun platform(): String {
    return "ios"
}

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun PdfColumn(url: String, modifier: Modifier) {
    val nsUrl = NSURL(string = url)
    val document = PDFDocument(uRL = nsUrl)

    val view = PDFView().apply {
        this.document = document
        this.autoScales = true
        this.displayMode =
            1 // Represent Enum entry 1 ( Single Page Continuous ) from PDFDisplayMode
        this.displayDirection = 0 // Represent Enum entry 1 ( Horizontal ) from PDFDisplayDirection
    }

    UIKitView(
        factory = { view },
        modifier = modifier,
    )
}