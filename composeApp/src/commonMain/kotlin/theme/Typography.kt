package theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import bug_freebio.composeapp.generated.resources.Lato_Regular
import bug_freebio.composeapp.generated.resources.Poppins_Regular
import bug_freebio.composeapp.generated.resources.Res
import bug_freebio.composeapp.generated.resources.WorkSans_Regular
import bug_freebio.composeapp.generated.resources.nunito_sans
import org.jetbrains.compose.resources.Font

@Composable
fun LatoFontFamily() = FontFamily(
    Font(Res.font.Lato_Regular, weight = FontWeight.Normal),
)

@Composable
fun PoppinsFontFamily() = FontFamily(
    Font(Res.font.Poppins_Regular, weight = FontWeight.Normal),
)

@Composable
fun WorkSansFontFamily() = FontFamily(
    Font(Res.font.WorkSans_Regular, weight = FontWeight.Normal),
)

@Composable
fun NunitoSansFamily() = FontFamily(
    Font(Res.font.nunito_sans, weight = FontWeight.Normal),
)

@Composable
fun BioTypography() = Typography().run {

    val nunitoSansFamily = NunitoSansFamily()
    val latoFontFamily = LatoFontFamily()
    val poppinsFontFamily = PoppinsFontFamily()
    val workSansFontFamily = WorkSansFontFamily()

    copy(
        displayLarge = displayLarge.copy(fontFamily = workSansFontFamily),
        displayMedium = displayMedium.copy(fontFamily = workSansFontFamily),
        displaySmall = displaySmall.copy(fontFamily = workSansFontFamily),
        headlineLarge = headlineLarge.copy(fontFamily = workSansFontFamily),
        headlineMedium = headlineMedium.copy(fontFamily = workSansFontFamily),
        headlineSmall = headlineSmall.copy(fontFamily = workSansFontFamily),
        titleLarge = titleLarge.copy(fontFamily = workSansFontFamily),
        titleMedium = titleMedium.copy(fontFamily = workSansFontFamily),
        titleSmall = titleSmall.copy(fontFamily = nunitoSansFamily),
        bodyLarge = bodyLarge.copy(fontFamily = nunitoSansFamily),
        bodyMedium = bodyMedium.copy(fontFamily = nunitoSansFamily),
        bodySmall = bodySmall.copy(fontFamily = nunitoSansFamily),
        labelLarge = labelLarge.copy(fontFamily = nunitoSansFamily),
        labelMedium = labelMedium.copy(fontFamily = nunitoSansFamily),
        labelSmall = labelSmall.copy(fontFamily = nunitoSansFamily)
    )
}