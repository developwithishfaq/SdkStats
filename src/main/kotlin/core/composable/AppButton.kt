package core.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun AppButton(
    text: String = "Create",
    icon: ImageVector? = Icons.Default.Add,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .defaultMinSize(100.dp)
            .background(
                color = core.Colors.WhiteShadeOne,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable {
                onClick.invoke()
            }
            .padding(horizontal = 10.dp, vertical = 5.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon?.let {
            Image(
                imageVector = it,
                contentDescription = null,
                modifier = Modifier
                    .size(22.dp)
            )
        }
        HorizontalSpace(5)
        SmallTextViewNoFont(text)
    }
}