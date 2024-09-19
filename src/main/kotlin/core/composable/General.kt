package core.composable


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.Colors


@Composable
fun VerticalSpace(space: Int = 10) {
    Spacer(
        modifier = Modifier
            .height((space * 1.3).dp)
    )
}

@Composable
fun HorizontalSpace(space: Int = 10) {
    Spacer(
        modifier = Modifier
            .width((space * 1.3).dp)
    )
}


@Composable
fun HeadingTextView(
    text: String,
    fontSize: Int = 16,
    color: Color = Colors.White,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = (fontSize * 1.3).sp,
        color = color,
        modifier = modifier
    )
}

@Composable
fun MediumTextView(
    text: String,
    fontSize: Int = 14,
    color: Color = Colors.Black,
    maxlines: Int = 23323,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        fontSize = (fontSize * 1.3).sp,
        color = color,
        maxLines = maxlines,
        modifier = modifier,
        textAlign = textAlign
    )
}

@Composable
fun MediumTextViewNoFont(
    text: String,
    fontSize: Int = 14,
    color: Color = Colors.White,
    maxlines: Int = 23323,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        fontSize = (fontSize * 1.3).sp,
        color = color,
        maxLines = maxlines,
        modifier = modifier,
        textAlign = textAlign
    )
}

@Composable
fun MediumNormalTextView(
    text: String,
    fontSize: Int = 13,
    color: Color = Colors.Black,
    maxlines: Int = 23323,
    fontWeight: FontWeight = FontWeight.Normal

) {
    Text(
        text = text,
        fontSize = (fontSize * 1.3).sp,
        color = color,
        maxLines = maxlines,
        fontWeight = fontWeight
    )
}
/*
@Composable
fun MediumTextViewNoFont(
    text: String,
    fontSize: Int = 13,
    color: Color = Colors.White,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = (fontSize * 1.3).sp,
        color = color,
        modifier = modifier,
        textAlign = TextAlign.Center
    )
}*/

@Composable
fun LoadingBox() {
    Box(
        Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = Colors.MainColor)
    }
}

@Composable
fun MessageBox(text: String, onClick: (() -> Unit)? = null) {
    Box(
        Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            MediumTextView(text)
            VerticalSpace(5)
            BlueButton("Retry", onClick = {
                onClick?.invoke()
            })
        }
    }
}

@Composable
fun SmallTextViewNoFont(
    text: String,
    fontSize: Int = 11,
    color: Color = Colors.Black,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    shouldVerticalScroll: Boolean = false,
) {
    val scroll =
        if (shouldVerticalScroll) Modifier.verticalScroll(rememberScrollState()) else modifier

    Text(
        text = text,
        fontSize = (fontSize * 1.3).sp,
        color = color,
        modifier = if (onClick != null) {
            scroll.clickable {
                onClick.invoke()
            }
        } else {
            scroll
        },
    )
}

@Composable
fun SmallTextViewWithFont(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: Int = 11,
    textAlign: TextAlign = TextAlign.Start,
    maxlines: Int = 23323,

    color: Color = Colors.Black,
    onClick: (() -> Unit)? = null
) {
    onClick?.let {
        TextButton(modifier = modifier, onClick = { onClick.invoke() }) {
            Text(
                text = text,
                fontSize = (fontSize * 1.3).sp,
                color = color,
            )
        }
    } ?: run {
        Text(
            modifier = modifier,
            text = text,
            fontSize = (fontSize * 1.3).sp,
            color = color,
            textAlign = textAlign,
            maxLines = maxlines,
            overflow = TextOverflow.Ellipsis
        )
    }
}
