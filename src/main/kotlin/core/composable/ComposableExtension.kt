package core.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType


@Composable
fun TextWithTextField(
    heading: String,
    text: String,
    modifier: Modifier = Modifier,
    distanceBetween: Int = 5,
    keyboardType: KeyboardType = KeyboardType.Text,
    onTextChange: (String) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        MediumNormalTextView(heading)
        VerticalSpace(distanceBetween)
        NormalTextField(text, onTextChange = onTextChange, keyboardType = keyboardType)
    }
}