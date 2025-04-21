package shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

expect fun platform(): String

@Composable
expect fun PdfColumn(url: String, modifier: Modifier = Modifier)