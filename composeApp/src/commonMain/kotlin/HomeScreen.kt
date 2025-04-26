import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import bug_freebio.composeapp.generated.resources.Res
import bug_freebio.composeapp.generated.resources.ic_close
import bug_freebio.composeapp.generated.resources.ic_file
import bug_freebio.composeapp.generated.resources.profile_pic
import contact.ContactSection
import model.experiences
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import shared.PdfColumn
import shared.platform

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    isCompactModeEnabledForWeb: Boolean = false
) {

    var showResume by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(Res.drawable.profile_pic),
                contentDescription = "profile-pic",
                modifier = Modifier
                    .padding(top = 30.dp, bottom = 30.dp)
                    .width(200.dp)
                    .clip(CircleShape)
            )

            Text(
                text = "Hi, I'm Ayush Patel",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.primary,
                letterSpacing = 1.2.sp,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.headlineLarge
            )

            Text(
                text = "(Your Go-To Android Geek)",
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                letterSpacing = 1.2.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
            )

            Text(
                text =
                    buildAnnotatedString {
                        append(introductionText1)
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(experiences[1].title ?: "Android Engineer")
                        }
                        append(introductionText2)
                    },
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth().padding(top = 30.dp),
                color = MaterialTheme.colorScheme.secondary,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                lineHeight = 1.5.em,
            )

            Text(
                text = "Skilled in",
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
                style = if (isCompactModeEnabledForWeb) MaterialTheme.typography.headlineLarge else MaterialTheme.typography.headlineSmall,
                letterSpacing = 1.2.sp,
                modifier = Modifier.fillMaxWidth().padding(top = 40.dp),
            )

            SkillSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            )

            if (isCompactModeEnabledForWeb) {
                ContactSection(Modifier.padding(top = 50.dp))
            } else {
                Spacer(Modifier.height(120.dp))
            }
        }

        FloatingActionButton(
            modifier = Modifier.padding(bottom = 30.dp).align(Alignment.BottomEnd),
            backgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
            onClick = {
                showResume = true
            },
            shape = RoundedCornerShape(15.dp),
            content = {
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_file),
                        contentDescription = "Resume",
                        modifier = Modifier
                            .padding(end = 5.dp)
                            .size(20.dp)
                            .clickable {
                                showResume = !showResume
                            },
                        tint = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                    Text(
                        text = "Resume",
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        )
    }
    if (showResume) {
        if (platform() == "android" || platform() == "ios") {
            Box(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
                PdfColumn(
                    url = repo + resume_url,
                    modifier = Modifier.padding(top = 100.dp)
                        .fillMaxSize()
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

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}