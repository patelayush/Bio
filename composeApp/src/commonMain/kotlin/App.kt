import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.compose.backgroundDark
import com.example.compose.backgroundLight
import com.example.compose.primaryDark
import com.example.compose.primaryLight
import com.example.compose.tertiaryDark
import com.example.compose.tertiaryLight
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(if (isSystemInDarkTheme()) backgroundDark else backgroundLight)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Hello There.!",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                color = if (isSystemInDarkTheme()) primaryDark else primaryLight,
                fontSize = MaterialTheme.typography.h4.fontSize
            )

            val a = arrayOf(1, 2, 3)
            print(a.distinct())
            Text(
                text = introductionText,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
                color = if (isSystemInDarkTheme()) tertiaryDark else tertiaryLight,
                fontSize = MaterialTheme.typography.body2.fontSize,
                lineHeight = 1.3.em,
            )
        }
    }
}