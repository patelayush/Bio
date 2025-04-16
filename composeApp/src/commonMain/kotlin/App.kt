import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.compose.BioTheme
import timeline.TimelineScreen

@Composable
fun App(dynamicThemingAvailable: Boolean) {
    val currentRoute = rememberSaveable { mutableStateOf(NavItem.HOME.label) }

    BioTheme(
        dynamic = dynamicThemingAvailable
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(
                    top = ScaffoldDefaults.contentWindowInsets.asPaddingValues()
                        .calculateTopPadding(),
                ),
            bottomBar = {
                BottomNavigationBar(
                    currentRoute = currentRoute.value,
                    onItemClick = {
                        currentRoute.value = it
                    }
                )
            },
            backgroundColor = MaterialTheme.colorScheme.background
        ) {
            Box(
                Modifier.fillMaxSize()
                    .padding(it),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .widthIn(max = 650.dp)
                ) {
                    when (currentRoute.value) {
                        NavItem.HOME.label -> HomeScreen()
                        NavItem.ABOUT.label -> TimelineScreen()
                        NavItem.CONTACT.label -> { /*TODO()*/
                        }
                    }
                }
            }
        }
    }
}