package timeline

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.experiences
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TimelineScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        /*stickyHeader {
            Text(
                text = "Timeline",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.secondary,
                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            )
        }*/
        itemsIndexed(experiences) { index, item ->
            TimelineNode(
                modifier = Modifier.padding(
                    top = if (index == 0) 30.dp else 0.dp,
                    bottom = if (index == experiences.lastIndex) 30.dp else 0.dp
                ),
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
}

@Composable
@Preview
fun TimelineScreenPreview() {
    TimelineScreen()
}