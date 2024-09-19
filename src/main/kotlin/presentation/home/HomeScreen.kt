package presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.Colors
import core.composable.CustomRadioButtons
import core.composable.NormalCard
import core.composable.SmallTextViewNoFont
import core.composable.VerticalSpace
import core.constants.Constants.capFirstWord
import domain.RequestStats
import domain.getMatchRates
import domain.getShowRates
import org.koin.compose.koinInject
import presentation.home.event.HomeEvents

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinInject()
) {
    val state by viewModel.state.collectAsState()
    val stats by viewModel.stats.collectAsState()

    val list = if (state.selectedAdType.isNotBlank()) {
        stats.filter {
            state.selectedAdType == it.adType
        }
    } else {
        stats
    }.sortedByDescending {
        it.request
    }
    LaunchedEffect(Unit) {
        viewModel.refresh()
    }

    Column {
        VerticalSpace(5)
        LazyRow {
            items(stats.distinctBy { it.adType }) { model ->
                CustomRadioButtons(
                    selected = state.selectedAdType == model.adType,
                    onClick = {
                        viewModel.onEvent(HomeEvents.SetAdType(model.adType))
                    },
                    title = model.adType,
                    desc = ""
                )
            }
        }
        VerticalSpace()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val modifier = Modifier
                .defaultMinSize(120.dp)
            NormalCard(
                text = list.sumOf {
                    it.request
                }.toString(),
                descText = "Requests",
                modifier = modifier
            )
            NormalCard(
                text = list.sumOf {
                    it.loaded
                }.toString(),
                descText = "Matched",
                modifier = modifier
            )
            NormalCard(
                text = list.sumOf {
                    it.failed
                }.toString(),
                descText = "Failed",
                modifier = modifier
            )
            NormalCard(
                text = list.getShowRates(),
                descText = "Show Rates",
                modifier = modifier
            )
            NormalCard(
                text = list.getMatchRates(),
                descText = "Match Rates",
                modifier = modifier
            )
        }
        VerticalSpace()
        LazyColumn {
            item {
                StatsRow()
            }
            items(list) { request ->
                StatsRow(request)
            }
        }
    }
}

@Composable
fun StatsRow(
    model: RequestStats? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 6.dp)
            .background(model?.let {
                Colors.Transparent
            } ?: run {
                Colors.WhiteShadeOne
            }, RoundedCornerShape(3.dp))
            .padding(vertical = 4.dp, horizontal = 6.dp)
    ) {
        SmallTextViewNoFont(
            modifier = Modifier
                .defaultMinSize(130.dp),
            text = model?.let {
                model.adKey + " (${model.adType.capFirstWord()})"
            } ?: run {
                "Key"
            }
        )
        model?.let {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SmallTextViewNoFont(
                    text = model.request.toString(),
                    modifier = Modifier
                        .weight(1f)
                )
                SmallTextViewNoFont(
                    text = model.loaded.toString(),
                    modifier = Modifier
                        .weight(1f)
                )
                SmallTextViewNoFont(
                    text = model.impression.toString(),
                    modifier = Modifier
                        .weight(1f)
                )
                SmallTextViewNoFont(
                    text = model.failed.toString(),
                    modifier = Modifier
                        .weight(1f)
                )
                SmallTextViewNoFont(
                    text = model.getShowRates(),
                    modifier = Modifier
                        .weight(1f)
                )
                SmallTextViewNoFont(
                    text = model.getMatchRates(),
                    modifier = Modifier
                        .weight(1f)
                )
            }
        } ?: run {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SmallTextViewNoFont(
                    text = "Request",
                    modifier = Modifier
                        .weight(1f)
                )
                SmallTextViewNoFont(
                    text = "Matched",
                    modifier = Modifier
                        .weight(1f)
                )
                SmallTextViewNoFont(
                    text = "Impression",
                    modifier = Modifier
                        .weight(1f)
                )
                SmallTextViewNoFont(
                    text = "No Fills",
                    modifier = Modifier
                        .weight(1f)
                )
                SmallTextViewNoFont(
                    text = "Show Rates",
                    modifier = Modifier
                        .weight(1f)
                )
                SmallTextViewNoFont(
                    text = "Match Rates",
                    modifier = Modifier
                        .weight(1f)
                )
            }
        }
    }
}