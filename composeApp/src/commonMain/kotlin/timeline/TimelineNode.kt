package timeline

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import model.Experience

@Composable
fun TimelineNode(
    modifier: Modifier = Modifier,
    experience: Experience,
    showDashedLines: Pair<Boolean, Boolean>,
    circleParameters: CircleParameters,
    isLastNode: Boolean = false,
    position: Int = 0,
    contentStartOffset: Dp = 0.dp,
    spacerBetweenNodes: Dp = 32.dp,
    content: @Composable() (BoxScope.(modifier: Modifier) -> Unit)
) {
    val verticalLineColor = MaterialTheme.colorScheme.secondary
    val horizontalLineColor = MaterialTheme.colorScheme.inversePrimary
    val textYear = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onPrimaryContainer)
    val yearLayoutResult = rememberTextMeasurer(256).measure(
        text = experience.year.toString(),
        style = textYear
    )
    val showInLeft = position % 2 == 0
    Box(
        modifier = modifier
            .fillMaxWidth()
            .drawBehind {
                val circleRadiusInPx = circleParameters.radius.toPx()
                val centerHeight = this.size.height / 2
                val centerWidth = this.size.width / 2
                // top half od the line
                drawLine(
                    color = verticalLineColor,
                    start = Offset(
                        centerWidth,
                        if (position == 0) centerHeight - 16.dp.toPx() else 0f
                    ),
                    end = Offset(
                        centerWidth,
                        centerHeight - 16.dp.toPx()
                    ),
                    strokeWidth = 5.dp.toPx(),
                    pathEffect = if (showDashedLines.first)
                        PathEffect.dashPathEffect(floatArrayOf(5f, 10f))
                    else null
                )
                // bottom half od the line
                if(!isLastNode) {
                    drawLine(
                        color = verticalLineColor,
                        start = Offset(
                            centerWidth,
                            centerHeight - 16.dp.toPx()
                        ),
                        end = Offset(
                            centerWidth,
                            this.size.height
                        ),
                        strokeWidth = 5.dp.toPx(),
                        pathEffect = if (showDashedLines.second)
                            PathEffect.dashPathEffect(floatArrayOf(5f, 10f))
                        else null
                    )
                }
                if (experience.title != null) {
                    drawLine(
                        color = horizontalLineColor,
                        start = Offset(
                            if (showInLeft) 0f else centerWidth,
                            if (isLastNode) centerHeight else centerHeight - 16.dp.toPx()
                        ),
                        end = Offset(
                            if (showInLeft) centerWidth else this.size.width,
                            if (isLastNode) centerHeight else centerHeight - 16.dp.toPx()
                        ),
                        strokeWidth = 3.dp.toPx(),
                    )
                }
                drawCircle(
                    color = circleParameters.backgroundColor,
                    radius = circleRadiusInPx,
                    center = Offset(
                        centerWidth,
                        if (isLastNode) centerHeight else centerHeight - 16.dp.toPx()
                    )
                )
                drawText(
                    textLayoutResult = yearLayoutResult,
                    topLeft = Offset(
                        centerWidth - yearLayoutResult.size.width / 2,
                        if (isLastNode) centerHeight - yearLayoutResult.size.height / 2 else centerHeight - 16.dp.toPx() - yearLayoutResult.size.height / 2
                    )
                )

            }
            .padding(
                start = contentStartOffset,
                bottom = if (!isLastNode) {
                    spacerBetweenNodes
                } else {
                    0.dp
                }
            )

    ) {
        if (experience.title != null) {
            content(Modifier.align(if (showInLeft) Alignment.CenterStart else Alignment.CenterEnd))
        } else {
            Spacer(Modifier.height(30.dp))
        }
    }
}

data class CircleParameters(
    val radius: Dp,
    val backgroundColor: Color
)

