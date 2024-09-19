package core.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.Colors


@Composable
fun NormalSwitch(text: String, checked: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(horizontal = 2.dp, vertical = 2.dp)
            .background(Colors.White)
            .padding(horizontal = 10.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        MediumTextView(
            text
        )
        HorizontalSpace(5)
        Switch(
            checked,
            onCheckedChange = {
                onClick.invoke()
            }
        )
    }
}