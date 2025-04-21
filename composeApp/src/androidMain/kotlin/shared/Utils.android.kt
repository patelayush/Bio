package shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.rememberVerticalPdfReaderState

actual fun platform(): String {
    return "android"
}

@Composable
actual fun PdfColumn(url: String, modifier: Modifier) {
    val pdfState = rememberVerticalPdfReaderState(
        resource = ResourceType.Remote(url),
        isZoomEnable = true,
        isAccessibleEnable = true,
    )

    if (!pdfState.isLoaded) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        VerticalPDFReader(
            state = pdfState,
            modifier = modifier,
        )
    }
}