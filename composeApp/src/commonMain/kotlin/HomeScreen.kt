import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import bug_freebio.composeapp.generated.resources.Res
import bug_freebio.composeapp.generated.resources.profile_pic
import components.BoldText
import components.ProjectSection
import components.SkillSection
import components.skills
import contact.ContactSection
import model.experiences
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    isCompactModeEnabledForWeb: Boolean = false
) {
    Box(
        modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .then(
                    if (isCompactModeEnabledForWeb) {
                        Modifier
                    } else {
                        Modifier.verticalScroll(rememberScrollState())
                    }
                ),
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

            BoldText(
                text = "$introductionText1**(${experiences[1].title ?: "Android Engineer"})**$introductionText2",
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
                letterSpacing = 1.1.sp,
                modifier = Modifier.fillMaxWidth().padding(top = 40.dp),
            )

            SkillSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                skills = skills
            )

            if(!isCompactModeEnabledForWeb) {
                ProjectSection(
                    modifier = Modifier,
                    isCompactModeEnabledForWeb = isCompactModeEnabledForWeb
                )
            }

            if (isCompactModeEnabledForWeb) {
                ContactSection(Modifier.padding(top = 50.dp))
            } else {
                Spacer(Modifier.height(120.dp))
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}