package shared

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.core.net.toUri
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.rememberVerticalPdfReaderState
import email
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import model.Message

actual object AppContext {
    private lateinit var application: Application

    fun setUp(context: Context) {
        application = context as Application
    }

    fun get(): Context {
        if (::application.isInitialized.not()) throw Exception("Application context isn't initialized")
        return application.applicationContext
    }
}

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

actual suspend fun sendEmail(message: Message) {
    val context = AppContext.get()

    return withContext(Dispatchers.IO) {
        try {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = "mailto:".toUri() // only email apps should handle this
                putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                putExtra(Intent.EXTRA_SUBJECT, message.getSubject())
                putExtra(Intent.EXTRA_TEXT, message.body)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            if (intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

@Composable
actual fun isTabletVersion(): Boolean {
    val configuration = LocalConfiguration.current
    return if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        configuration.screenWidthDp > 840
    } else {
        configuration.screenWidthDp > 600
    }
}