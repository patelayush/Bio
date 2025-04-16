import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import bug_freebio.composeapp.generated.resources.Res
import bug_freebio.composeapp.generated.resources.about
import bug_freebio.composeapp.generated.resources.contact_us
import bug_freebio.composeapp.generated.resources.home
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun BottomNavigationBar(
    currentRoute: String? = null,
    onItemClick: (String) -> Unit = {}
) {
    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp + AppBarDefaults.bottomAppBarWindowInsets.asPaddingValues().calculateBottomPadding()),
        backgroundColor = MaterialTheme.colorScheme.surfaceContainer,
        elevation = AppBarDefaults.BottomAppBarElevation,
        windowInsets = AppBarDefaults.bottomAppBarWindowInsets
    ) {
        NavItem.entries.forEach { entry ->
            BottomNavigationItem(
                selected = currentRoute == entry.label,
                onClick = { onItemClick(entry.label) },
                icon = {
                    Image(
                        painter = painterResource(entry.icon),
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary)
                    )
                },
                label = {
                    Text(
                        text = entry.label,
                        fontWeight = if (currentRoute == entry.label) FontWeight.Bold else FontWeight.Normal,
                    )
                },
                selectedContentColor = MaterialTheme.colorScheme.onSurface,
                unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                alwaysShowLabel = false
            )
        }
    }
}

enum class NavItem(
    val label: String,
    val icon: DrawableResource,
) {
    HOME(
        label = "Home",
        icon = Res.drawable.home,
    ),
    ABOUT(
        label = "Experience",
        icon = Res.drawable.about,
    ),
    CONTACT(
        label = "Contact",
        icon = Res.drawable.contact_us,
    ),
}