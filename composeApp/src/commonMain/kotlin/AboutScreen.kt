import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import bug_freebio.composeapp.generated.resources.Res
import bug_freebio.composeapp.generated.resources.app_icon
import org.appsmith.bio.BuildKonfig
import org.jetbrains.compose.resources.painterResource

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.BottomCenter
    ) {
        AboutScreenContent(modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun AboutScreenContent(modifier: Modifier = Modifier) {
    val uriHandler = LocalUriHandler.current
    Column(
        modifier = modifier
            .padding(bottom = 50.dp)
            .padding(horizontal = 20.dp)
            .widthIn(max = 650.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(Res.drawable.app_icon),
            contentDescription = "icon",
            modifier = Modifier.size(140.dp)
        )
        Column(
            modifier = Modifier.padding(top = 20.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = appname,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                text = "v${BuildKonfig.version}",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface,
                letterSpacing = 0.1.em
            )
        }
        Text(
            text = "This app is a dynamic representation of my professional path and the skills I've acquired. Built with Compose Multiplatform, this app demonstrates my commitment to modern and efficient development practices.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(top = 30.dp)
        )
        Column(
            modifier = Modifier.padding(top = 30.dp).fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            Text(
                text = "Developed by:",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                text = "Ayush Patel",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.clickable {
                    uriHandler.openUri(linkedinLink)
                }
            )
        }
    }
    Text(
        modifier = Modifier.padding(bottom = 20.dp).fillMaxWidth(),
        text = "Copyright © 2025 AppSmith",
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.onSurface,
    )
}

