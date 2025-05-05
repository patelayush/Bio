package timeline

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import components.ProjectSection
import model.experiences
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TimelineScreen(
    modifier: Modifier = Modifier,
    isCompactModeEnabledForWeb: Boolean = false
) {
    Column(
        modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "The Road So Far",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 30.dp, bottom = if(!isCompactModeEnabledForWeb) 30.dp else 0.dp).fillMaxWidth(),
            color = MaterialTheme.colorScheme.primary,
            letterSpacing = 1.2.sp,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.headlineLarge
        )
        Column(
            Modifier.then(
                if (isCompactModeEnabledForWeb) {
                    Modifier
                } else {
                    Modifier.verticalScroll(rememberScrollState())
                }
            )
        ) {
            experiences.forEachIndexed { index, item ->
                TimelineNode(
                    modifier = Modifier.padding(
                        top = if (index == 0) 30.dp else 0.dp,
                        bottom = if (index == experiences.lastIndex) 30.dp else 0.dp
                    ),
                    spacerBetweenNodes = if (isCompactModeEnabledForWeb) 60.dp else 40.dp,
                    experience = item,
                    circleParameters = CircleParameters(
                        20.dp,
                        MaterialTheme.colorScheme.secondaryContainer
                    ),
                    isLastNode = experiences.lastIndex == index,
                    position = index,
                    showDashedLines = if (index == 0 || index == experiences.lastIndex) Pair(
                        false,
                        false
                    )
                    else Pair(
                        ((experiences[index - 1].year - experiences[index].year) > 1),
                        ((experiences[index].year - experiences[index + 1].year) > 1)
                    ),
                    content = {
                        ExperienceCard(
                            modifier = it,
                            experience = item,
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        )
                    }
                )
            }
        }

        if(isCompactModeEnabledForWeb) {
            ProjectSection(
                modifier = Modifier,
                isCompactModeEnabledForWeb = isCompactModeEnabledForWeb
            )
        }

        Spacer(Modifier.height(30.dp))
    }
}

@Composable
@Preview
fun TimelineScreenPreview() {
    TimelineScreen()
}