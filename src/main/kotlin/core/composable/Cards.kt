package core.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.Colors


@Composable
fun NormalCard(
    text: String,
    descText: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        backgroundColor = Colors.WhiteShadeOne,
        contentColor = Colors.Black,
    ) {
        Column(
            modifier = Modifier
                .background(Colors.WhiteShadeOne)
                .padding(
                    horizontal = 10.dp,
                    vertical = 15.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            MediumTextView(
                text = text,
                fontSize = 18
            )
            VerticalSpace(5)
            MediumTextView(
                text = descText,
                fontSize = 12
            )
        }
    }
}