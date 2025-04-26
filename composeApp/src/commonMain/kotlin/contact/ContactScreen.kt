package contact

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bug_freebio.composeapp.generated.resources.Res
import bug_freebio.composeapp.generated.resources.ic_mail
import bug_freebio.composeapp.generated.resources.linkedin_logo
import email
import kotlinx.coroutines.launch
import linkedinLink
import model.Message
import org.jetbrains.compose.resources.painterResource
import shared.sendEmail

@Composable
fun ContactScreen(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier.padding(horizontal = 30.dp).verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ContactSection(Modifier.padding(top = 40.dp))
    }
}

@Composable
fun ContactSection(modifier: Modifier = Modifier) {
    var senderName by remember { mutableStateOf(TextFieldValue("")) }
    var senderEmail by remember { mutableStateOf(TextFieldValue("")) }
    var senderMessage by remember { mutableStateOf(TextFieldValue("")) }
    var isSenderNameValid by remember { mutableStateOf(true) }
    var isSenderEmailValid by remember { mutableStateOf(true) }
    var isSenderMessageValid by remember { mutableStateOf(true) }
    val uriHandler = LocalUriHandler.current
    var hasUserInteracted by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val isEnabled =
        hasUserInteracted && isSenderNameValid && isSenderEmailValid && isSenderMessageValid

    fun isValidForm(): Boolean {
        isSenderNameValid = senderName.text.isNotEmpty()
        isSenderEmailValid = senderEmail.text.isNotEmpty()
        isSenderMessageValid = senderMessage.text.isNotEmpty()
        return isSenderNameValid && isSenderEmailValid && isSenderMessageValid
    }

    Text(
        text = "Get in Touch",
        textAlign = TextAlign.Center,
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.primary,
        letterSpacing = 1.2.sp,
        fontWeight = FontWeight.SemiBold,
        style = MaterialTheme.typography.headlineLarge
    )

    OutlinedCard(
        modifier = Modifier.fillMaxWidth().padding(top = 30.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.outlinedCardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary
        ),
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.secondary)
    ) {
        Column(
            Modifier.padding(horizontal = 15.dp, vertical = 30.dp),
            horizontalAlignment = Alignment.End
        ) {
            CustomTextField(
                modifier = Modifier,
                text = senderName,
                label = "Your Name",
                errorText = "Please enter your name.",
                onValueChange = {
                    senderName = it
                    isSenderNameValid = it.text.isNotEmpty()
                    hasUserInteracted = true
                },
                isError = !isSenderNameValid
            )

            CustomTextField(
                modifier = Modifier.padding(top = 20.dp),
                text = senderEmail,
                label = "Your Email",
                errorText = "Please enter your email.",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                onValueChange = {
                    senderEmail = it
                    isSenderEmailValid = it.text.isNotEmpty()
                    hasUserInteracted = true
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(Res.drawable.ic_mail),
                        contentDescription = "Email",
                        tint = if (!isSenderEmailValid) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.secondary,
                    )
                },
                isError = !isSenderEmailValid
            )

            CustomTextField(
                modifier = Modifier.padding(top = 20.dp).heightIn(min = 150.dp),
                text = senderMessage,
                label = "Message",
                errorText = "Please enter your message.",
                onValueChange = {
                    senderMessage = it
                    isSenderMessageValid = it.text.isNotEmpty()
                    hasUserInteracted = true
                },
                isError = !isSenderMessageValid,
                singleLine = false,
            )

            Button(
                enabled = isEnabled,
                modifier = Modifier.padding(top = 20.dp),
                onClick = {
                    if (isValidForm()) {
                        scope.launch {
                            sendEmail(
                                Message(
                                    name = senderName.text,
                                    email = senderEmail.text,
                                    body = senderMessage.text
                                )
                            )
                        }
                    }
                },
                shape = RoundedCornerShape(10.dp),
                elevation = ButtonDefaults.elevation(5.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
                    disabledBackgroundColor = MaterialTheme.colorScheme.tertiaryContainer.copy(
                        alpha = 0.1f
                    )
                ),
                content = {
                    Text(
                        text = "Send Message",
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp),
                        fontWeight = FontWeight.SemiBold
                    )
                }
            )
        }
    }

    Column(Modifier.padding(top = 20.dp)) {
        Text(
            text = "Or, you can reach out to me via",
            color = MaterialTheme.colorScheme.primary,
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
            fontWeight = FontWeight.SemiBold
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_mail),
                modifier = Modifier.height(MaterialTheme.typography.titleSmall.fontSize.value.dp),
                tint = MaterialTheme.colorScheme.secondary,
                contentDescription = "Email"
            )
            Text(
                text = email,
                color = MaterialTheme.colorScheme.secondary,
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {
                    scope.launch {
                        sendEmail(Message())
                    }
                }
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(Res.drawable.linkedin_logo),
                modifier = Modifier.height(MaterialTheme.typography.titleSmall.fontSize.value.dp),
                contentDescription = "LinkedIn",
            )
            Text(
                text = linkedinLink,
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                color = MaterialTheme.colorScheme.secondary,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {
                    uriHandler.openUri(linkedinLink)
                }
            )
        }
    }

    Spacer(Modifier.height(50.dp))
}

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    text: TextFieldValue,
    label: String = "",
    singleLine: Boolean = true,
    isError: Boolean,
    errorText: String = "",
    leadingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    onValueChange: (TextFieldValue) -> Unit
) {
    var textFieldValue by remember { mutableStateOf(text) }
    val textFieldColors = TextFieldDefaults.colors(
        focusedTextColor = MaterialTheme.colorScheme.primary,
        cursorColor = MaterialTheme.colorScheme.primary,
        focusedContainerColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        errorContainerColor = Color.Transparent,
        errorSupportingTextColor = Color.Transparent,
    )
    Column {
        TextField(
            value = textFieldValue,
            onValueChange = {
                textFieldValue = it
                onValueChange(textFieldValue)
            },
            modifier = modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(10.dp),
                    color = if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.secondary
                ),
            label = {
                Text(
                    text = label,
                    color = if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(bottom = 3.dp)
                )
            },
            textStyle = MaterialTheme.typography.titleMedium,
            colors = textFieldColors,
            singleLine = singleLine,
            leadingIcon = leadingIcon,
            keyboardOptions = keyboardOptions,
        )
        if (isError) {
            Text(
                text = errorText,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 3.dp)
            )
        }
    }
}