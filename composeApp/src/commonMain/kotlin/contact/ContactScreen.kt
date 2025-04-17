package contact

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bug_freebio.composeapp.generated.resources.Res
import bug_freebio.composeapp.generated.resources.linkedin_logo
import bug_freebio.composeapp.generated.resources.mail
import email
import linkedinLink
import org.jetbrains.compose.resources.painterResource

@Composable
fun ContactScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "Get in Touch",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.primary,
            letterSpacing = 1.2.sp,
            fontSize = MaterialTheme.typography.headlineLarge.fontSize
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(top=30.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ){
            Icon(
                painter = painterResource(Res.drawable.mail),
                modifier = Modifier.size(25.dp),
                tint = MaterialTheme.colorScheme.secondary,
                contentDescription = "Email"
            )
            Text(
                text = email,
                color = MaterialTheme.colorScheme.primary,
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(top=30.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ){
            Icon(
                painter = painterResource(Res.drawable.linkedin_logo),
                modifier = Modifier.size(25.dp),
                contentDescription = "LinkedIn"
            )
            Text(
                text = linkedinLink,
                color = MaterialTheme.colorScheme.primary,
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
        }
    }
}