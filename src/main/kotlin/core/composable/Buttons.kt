package core.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.Colors


@Composable
fun BlueButton(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: Int = 16,
    bold: Boolean = true,
    round: Int = 5,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Colors.MainColor, RoundedCornerShape(round.dp))
            .clickable {
                onClick.invoke()
            }
            .padding(horizontal = 35.dp, vertical = 15.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            fontSize = fontSize.sp,
            color = Colors.White,
            style = TextStyle(
                fontWeight = if (bold) {
                    FontWeight.Bold
                } else {
                    null
                }
            )
        )
    }
}