package components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import bug_freebio.composeapp.generated.resources.Res
import bug_freebio.composeapp.generated.resources.github_mark
import bug_freebio.composeapp.generated.resources.ic_app_store
import bug_freebio.composeapp.generated.resources.ic_compose_multiplatform
import bug_freebio.composeapp.generated.resources.ic_play_store
import model.Project
import model.projectsList
import org.jetbrains.compose.resources.painterResource

@Composable
fun ProjectSection(modifier: Modifier = Modifier, isCompactModeEnabledForWeb: Boolean) {
    val uriHandler = LocalUriHandler.current
    Column(modifier) {
        Text(
            text = "Projects",
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary,
            style = if (isCompactModeEnabledForWeb) MaterialTheme.typography.headlineLarge else MaterialTheme.typography.headlineSmall,
            letterSpacing = 1.1.sp,
            modifier = Modifier.fillMaxWidth().padding(top = 40.dp),
        )
        FeaturedProjectsCarousel(
            modifier = Modifier.padding(top = 10.dp),
            projects = projectsList,
            onLinkClick = {
                uriHandler.openUri(it)
            })
    }
}

@Composable
fun FeaturedProjectsCarousel(
    modifier: Modifier = Modifier,
    projects: List<Project>,
    onLinkClick: (String) -> Unit
) {
    val pagerState = rememberPagerState(initialPage = 0) { projects.size }
    var minHeight by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current

    HorizontalPager(
        state = pagerState,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        pageSpacing = 30.dp,
        beyondViewportPageCount = projects.size,
    ) { page ->
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = minHeight)
                .onSizeChanged {
                    density.run {
                        minHeight = max(minHeight, it.height.toDp())
                    }
                },
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        ) {
            val project = projects[page]
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = minHeight)
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    Modifier
                        .then(
                            if (project.tools != null) {
                                Modifier.fillMaxHeight()
                            } else {
                                Modifier.wrapContentHeight()
                            }
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = project.title,
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center,
                        letterSpacing = 1.1.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )

                    if (project.shortDescription != null) {
                        BoldText(
                            text = project.shortDescription,
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                    if (project.tools != null) {
                        val skillColorSet = getSkillColorSet().toMutableSet()
                        skillColorSet.remove(
                            Pair(
                                MaterialTheme.colorScheme.secondaryContainer,
                                MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        )
                        SkillSection(
                            modifier = Modifier.fillMaxWidth(),
                            skills = project.tools,
                            skillColorSet = remember { skillColorSet }
                        )
                    }
                    Box(Modifier.fillMaxHeight(), contentAlignment = Alignment.BottomCenter) {
                        Row(
                            modifier = Modifier.padding(top = 10.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (project.githubLink != null) {
                                Image(
                                    painter = painterResource(Res.drawable.github_mark),
                                    contentDescription = "Github",
                                    modifier = Modifier
                                        .clickable {
                                            onLinkClick(project.githubLink)
                                        }
                                        .size(24.dp),
                                )
                            }
                            if (project.webLink != null) {
                                Image(
                                    painter = painterResource(
                                        Res.drawable.ic_compose_multiplatform
                                    ),
                                    contentDescription = "Web link",
                                    modifier = Modifier
                                        .clickable {
                                            onLinkClick(project.webLink)
                                        }
                                        .size(30.dp),
                                )
                            }
                            if (project.playStoreLink != null) {
                                Image(
                                    painter = painterResource(
                                        Res.drawable.ic_play_store
                                    ),
                                    contentDescription = "Google PLay link",
                                    modifier = Modifier
                                        .clickable {
                                            onLinkClick(project.playStoreLink)
                                        }
                                        .size(24.dp),
                                )
                            }
                            if (project.appleStoreLink != null) {
                                Image(
                                    painter = painterResource(
                                        Res.drawable.ic_app_store
                                    ),
                                    contentDescription = "AppStore link",
                                    modifier = Modifier
                                        .clickable {
                                            onLinkClick(project.appleStoreLink)
                                        }
                                        .size(24.dp),
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    if (projects.size > 1) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            horizontalArrangement = Arrangement.spacedBy(3.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(projects.size) { index ->
                val isSelected = pagerState.currentPage == index
                IndicatorDot(isSelected = isSelected)
            }
        }
    }
}

@Composable
fun IndicatorDot(isSelected: Boolean) {
    val color =
        if (isSelected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.secondary.copy(
            alpha = 0.6f
        )
    val size = if (isSelected) 8.dp else 6.dp
    Spacer(
        modifier = Modifier
            .size(size)
            .background(color, shape = CircleShape)
            .padding(2.dp)
    )
}