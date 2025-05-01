package timeline

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import components.BoldText
import model.Experience

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExperienceSheet(
    experience: Experience,
    onDismissRequest: () -> Unit = {}
) {
    var openBottomSheet by rememberSaveable { mutableStateOf(true) }
    var skipPartiallyExpanded by rememberSaveable { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val bottomSheetState =
        rememberModalBottomSheetState(skipPartiallyExpanded = skipPartiallyExpanded)

    if (openBottomSheet) {
        ModalBottomSheet(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            onDismissRequest = {
                openBottomSheet = false
                onDismissRequest() },
            sheetState = bottomSheetState,
        ) {
            Box(Modifier.padding(bottom = 56.dp)) {
                ExperienceDetails(experience)
            }
        }
    }
}

@Composable
fun ExperienceDetails(experience: Experience) {
    Column(
        modifier = Modifier
            .padding(top = 20.dp)
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = experience.title ?: "",
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.primary,
            fontSize = MaterialTheme.typography.headlineSmall.fontSize
        )
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = experience.company ?: "",
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end =15.dp).weight(1f)
                )
                Text(
                    text = experience.location ?: "",
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize
                )
            }

            Text(
                text = experience.timePeriod ?: "",
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.secondary,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
            )
        }

        HorizontalDivider(Modifier.fillMaxWidth())

        Column(modifier = Modifier.verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            experience.description?.forEach {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        text = "•",
                        color = MaterialTheme.colorScheme.secondary,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    )
                    BoldText(
                        text = it,
                        color = MaterialTheme.colorScheme.secondary,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize
                    )
                }
            }
        }
    }
}
