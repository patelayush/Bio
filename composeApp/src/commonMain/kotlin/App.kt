import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.example.compose.BioTheme

@Composable
fun App(dynamicThemingAvailable: Boolean) {
    val currentRoute = rememberSaveable { mutableStateOf(NavItem.HOME.label) }

    BioTheme(
        dynamic = dynamicThemingAvailable
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize()
                .padding(top = ScaffoldDefaults.contentWindowInsets.asPaddingValues().calculateTopPadding()),
            bottomBar = {
                BottomNavigationBar(
                    currentRoute = currentRoute.value,
                    onItemClick = {
                        currentRoute.value = it
                    }
                )
            }
        ) {
            when (currentRoute.value) {
                NavItem.HOME.label -> HomeScreen()
                NavItem.ABOUT.label -> { /*TODO()*/
                }

                NavItem.CONTACT.label -> { /*TODO()*/
                }
            }
        }
    }
}