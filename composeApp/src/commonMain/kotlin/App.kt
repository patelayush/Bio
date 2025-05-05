import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import bug_freebio.composeapp.generated.resources.Res
import bug_freebio.composeapp.generated.resources.ic_close
import bug_freebio.composeapp.generated.resources.ic_compact
import bug_freebio.composeapp.generated.resources.ic_cozy
import bug_freebio.composeapp.generated.resources.ic_file
import com.example.compose.BioTheme
import components.BottomNavigationBar
import components.NavItem
import contact.ContactScreen
import org.jetbrains.compose.resources.painterResource
import shared.PdfColumn
import shared.isTabletVersion
import shared.platform
import timeline.TimelineScreen

@Composable
fun App(dynamicThemingAvailable: Boolean) {
    var currentRoute by rememberSaveable { mutableStateOf(NavItem.HOME.label) }
    var isCompactViewEnabledForWeb by rememberSaveable { mutableStateOf(false) }
    var showResume by rememberSaveable { mutableStateOf(false) }

    if (!isTabletVersion()) {
        isCompactViewEnabledForWeb = false
    }

    BioTheme(
        dynamic = dynamicThemingAvailable
    ) {
        Scaffold(
            modifier = Modifier
                .animateContentSize(tween())
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(
                    top = ScaffoldDefaults.contentWindowInsets.asPaddingValues()
                        .calculateTopPadding(),
                ),
            bottomBar = {
                if (!isCompactViewEnabledForWeb) {
                    BottomNavigationBar(
                        currentRoute = currentRoute,
                        onItemClick = {
                            currentRoute = it
                        }
                    )
                }
            },
            backgroundColor = MaterialTheme.colorScheme.background
        ) {
            Box(
                Modifier.fillMaxSize()
                    .padding(it)
                ,
            ) {
                if (!isCompactViewEnabledForWeb) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .widthIn(max = 650.dp)
                    ) {
                        when (currentRoute) {
                            NavItem.HOME.label -> HomeScreen(isCompactModeEnabledForWeb = isCompactViewEnabledForWeb)
                            NavItem.EXPERIENCE.label -> TimelineScreen(isCompactModeEnabledForWeb = isCompactViewEnabledForWeb)
                            NavItem.CONTACT.label -> ContactScreen(Modifier.fillMaxSize())
                            NavItem.ABOUT.label -> AboutScreen(Modifier.fillMaxSize())
                        }
                    }
                } else {
                    CompactViewForWeb(isCompactViewEnabledForWeb)
                }

                ResizeViewFAB(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(
                            top = ScaffoldDefaults.contentWindowInsets.asPaddingValues()
                                .calculateTopPadding() + 20.dp,
                            end = 20.dp
                        )
                        .alpha(if (isTabletVersion()) 1f else 0f),
                    compactViewEnabledForWeb = isCompactViewEnabledForWeb,
                    onClick = {
                        isCompactViewEnabledForWeb = !isCompactViewEnabledForWeb
                    })

                FloatingActionButton(
                    modifier = Modifier
                        .padding(bottom = 30.dp, end = 20.dp)
                        .align(Alignment.BottomEnd),
                    backgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
                    onClick = {
                        showResume = true
                    },
                    shape = RoundedCornerShape(15.dp),
                    content = {
                        val interactionSource = remember { MutableInteractionSource() }
                        val showFullTitle by interactionSource.collectIsHoveredAsState()

                        Row(
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .hoverable(interactionSource)
                                .animateContentSize(
                                    animationSpec = tween()
                                ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.ic_file),
                                contentDescription = "Resume",
                                modifier = Modifier
                                    .size(20.dp)
                                    .clickable {
                                        showResume = !showResume
                                    },
                                tint = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                            if (!isTabletVersion() || showFullTitle) {
                                Text(
                                    text = "Resume",
                                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier.padding(start=5.dp)
                                )
                            }
                        }
                    }
                )
                if (showResume) {
                    if (platform() == "android" || platform() == "ios") {
                        Box(
                            Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.background)
                        ) {
                            PdfColumn(
                                url = repo + resume_url,
                                modifier = Modifier.padding(top = 100.dp)
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colorScheme.background)
                            )
                            Box(
                                modifier = Modifier
                                    .padding(15.dp)
                                    .shadow(10.dp, shape = RoundedCornerShape(10.dp))
                                    .background(MaterialTheme.colorScheme.tertiaryContainer),
                            ) {
                                Icon(
                                    painter = painterResource(Res.drawable.ic_close),
                                    contentDescription = "Close",
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .clickable {
                                            showResume = !showResume
                                        },
                                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }
                        }
                    } else {
                        PdfColumn(
                            url = repo + resume_url
                        )
                        showResume = false
                    }
                }
            }
        }
    }
}

@Composable
fun ResizeViewFAB(
    modifier: Modifier = Modifier,
    compactViewEnabledForWeb: Boolean,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val showFullTitle by interactionSource.collectIsHoveredAsState()

    ElevatedCard(
        modifier = modifier
            .hoverable(interactionSource)
            .animateContentSize(
                animationSpec = tween()
            ).clickable {
                onClick()
            },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
        shape = RoundedCornerShape(10.dp),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = if (compactViewEnabledForWeb)
                    painterResource(Res.drawable.ic_cozy)
                else
                    painterResource(Res.drawable.ic_compact),
                contentDescription = if (compactViewEnabledForWeb) "Expanded View" else "Compact View",
                modifier = Modifier.size(20.dp),
                tint = MaterialTheme.colorScheme.onTertiaryContainer
            )
            if (showFullTitle) {
                Text(
                    text = if (compactViewEnabledForWeb) "Expanded View" else "Compact View",
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 5.dp)
                )
            }
        }
    }
}

@Composable
fun CompactViewForWeb(
    compactViewEnabledForWeb: Boolean
) {
    var height by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current

    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Row(Modifier.padding(top = 30.dp)) {
                HomeScreen(
                    modifier = Modifier.weight(0.45f).onSizeChanged {
                       density.run{
                           height = max(height, it.height.toDp())
                        }
                    },
                    isCompactModeEnabledForWeb = compactViewEnabledForWeb
                )
                Box(
                    modifier = Modifier.weight(0.1f),
                    contentAlignment = Alignment.Center
                ) {
                    VerticalDivider(
                        modifier = Modifier.wrapContentWidth().height(height),
                        color = MaterialTheme.colorScheme.tertiary,
                        thickness = 2.dp
                    )
                }
                TimelineScreen(
                    modifier = Modifier
                        .weight(0.45f).onSizeChanged {
                            density.run{
                                height = max(height, it.height.toDp())
                            }
                        },
                    isCompactModeEnabledForWeb = compactViewEnabledForWeb
                )
            }
        }
        item{
            Text(
                text = "About",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top=50.dp),
                letterSpacing = 1.2.sp,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.headlineSmall
            )
            AboutScreenContent(modifier = Modifier.padding(top=30.dp))
        }
        item{
            Spacer(Modifier.height(60.dp))
        }
    }
}
