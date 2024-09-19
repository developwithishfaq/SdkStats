package core.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.Colors
import org.koin.compose.koinInject
import presentation.keys.KeysViewModel
import presentation.keys.events.KeysScreenEvent

@Composable
fun KeysCard(
    viewModel: KeysViewModel = koinInject(),
) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.refresh()
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 6.dp),
        backgroundColor = Colors.White,
        contentColor = Colors.Black,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                "^",
                modifier = Modifier
                    .background(Colors.TextGrey, RoundedCornerShape(8.dp))
                    .clickable {
                        viewModel.onEvent(KeysScreenEvent.HideShow)
                    }
                    .padding(horizontal = 10.dp, vertical = 0.dp),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                color = Colors.White
            )
            VerticalSpace()
            AnimatedVisibility(state.showCard) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 10.dp)
                ) {
                    NormalTextField(
                        text = state.url,
                        placeHolder = "Database Url",
                        onTextChange = {
                            viewModel.onEvent(KeysScreenEvent.SetUrl(it))
                        }
                    )
                    VerticalSpace()
                    NormalTextField(
                        text = state.key,
                        placeHolder = "Database Secret Key",
                        onTextChange = {
                            viewModel.onEvent(KeysScreenEvent.SetKey(it))
                        }
                    )
                    VerticalSpace()
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        BlueButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            text = "Reset"
                        ) {
                            viewModel.onEvent(KeysScreenEvent.Reset)
                        }
                        BlueButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            text = "Save"
                        ) {
                            viewModel.onEvent(KeysScreenEvent.Update)
                        }
                    }
                }
            }
        }
    }
}