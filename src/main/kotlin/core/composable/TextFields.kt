package core.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import core.Colors

@Composable
fun NormalTextField(
    text: String,
    placeHolder: String = "",
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    onTextChange: (String) -> Unit
) {
    TextField(
        value = text,
        onValueChange = {
            onTextChange.invoke(it)
        },
        modifier = modifier
            .fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Colors.Transparent,
            textColor = Colors.Black
        ),
        placeholder = {
            Text(placeHolder)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        )
    )
}