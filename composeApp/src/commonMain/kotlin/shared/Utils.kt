package shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import model.Message


expect object AppContext

expect fun platform(): String

@Composable
expect fun PdfColumn(url: String, modifier: Modifier = Modifier)

expect suspend fun sendEmail(message: Message)

@Composable
expect fun isTabletVersion(): Boolean
