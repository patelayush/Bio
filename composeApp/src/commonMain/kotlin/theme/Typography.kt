package theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import bug_freebio.composeapp.generated.resources.Lato_Regular
import bug_freebio.composeapp.generated.resources.Poppins_Regular
import bug_freebio.composeapp.generated.resources.Res
import bug_freebio.composeapp.generated.resources.WorkSans_Regular
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
fun BioTypography() = Typography().run {

    val latoFontFamily = LatoFontFamily()
    val poppinsFontFamily = PoppinsFontFamily()
    val workSansFontFamily = WorkSansFontFamily()

    copy(
        displayLarge = displayLarge.copy(fontFamily = latoFontFamily),
        displayMedium = displayMedium.copy(fontFamily = latoFontFamily),
        displaySmall = displaySmall.copy(fontFamily = latoFontFamily),
        headlineLarge = headlineLarge.copy(fontFamily = poppinsFontFamily),
        headlineMedium = headlineMedium.copy(fontFamily = workSansFontFamily),
        headlineSmall = headlineSmall.copy(fontFamily = latoFontFamily),
        titleLarge = titleLarge.copy(fontFamily = poppinsFontFamily),
        titleMedium = titleMedium.copy(fontFamily = workSansFontFamily),
        titleSmall = titleSmall.copy(fontFamily = latoFontFamily),
        bodyLarge = bodyLarge.copy(fontFamily = poppinsFontFamily),
        bodyMedium = bodyMedium.copy(fontFamily = latoFontFamily),
        bodySmall = bodySmall.copy(fontFamily = latoFontFamily),
        labelLarge = labelLarge.copy(fontFamily = poppinsFontFamily),
        labelMedium = labelMedium.copy(fontFamily = latoFontFamily),
        labelSmall = labelSmall.copy(fontFamily = latoFontFamily)
    )
}