package `in`.hypernation.cryptowiki.screens.detail_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import `in`.hypernation.cryptowiki.R
import `in`.hypernation.cryptowiki.data.dto.Tag
import `in`.hypernation.cryptowiki.data.dto.Team
import `in`.hypernation.cryptowiki.domain.models.Coin
import `in`.hypernation.cryptowiki.screens.view_models.CoinDetailViewModel
import `in`.hypernation.cryptowiki.ui.theme.Green
import `in`.hypernation.cryptowiki.ui.theme.LightBlue
import `in`.hypernation.cryptowiki.ui.theme.Orange
import `in`.hypernation.cryptowiki.ui.theme.Red
import `in`.hypernation.cryptowiki.ui.theme.fonts


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DetailScreen(
    navController: NavController,
    viewModel: CoinDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        state.coin?.let { coin ->
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    Column(
                        modifier = Modifier.padding(horizontal = 30.dp)
                    ) {
                        Row(
                            Modifier.padding(vertical = 30.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .shadow(2.dp, RoundedCornerShape(15.dp))
                                    .background(
                                        MaterialTheme.colorScheme.surface,
                                        RoundedCornerShape(15.dp)
                                    )
                                    .clickable { navController.popBackStack() }

                            ) {
                                Image(
                                    imageVector = Icons.Rounded.ArrowBack,
                                    contentDescription = "back",
                                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.tertiary),
                                    modifier = Modifier
                                        .size(50.dp)
                                        .padding(12.dp)
                                )
                            }
                        }

                        BasicInfoItem(coin = coin)
                        Text(
                            text = coin.description,
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            style = MaterialTheme.typography.labelSmall,
                            lineHeight = 24.sp,
                            modifier = Modifier.padding(vertical = 15.dp, horizontal = 10.dp)
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = "TAGS",
                            color = MaterialTheme.colorScheme.tertiary,
                            fontFamily = fonts,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        FlowRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalArrangement = Arrangement.spacedBy(15.dp),
                            maxItemsInEachRow = 3
                        ) {
                            coin.tags?.let {tags ->
                                tags.forEach{tag ->
                                    CryptoTag(tag = tag)
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = "TEAM",
                            color = MaterialTheme.colorScheme.tertiary,
                            fontFamily = fonts,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.height(15.dp))

                    }
                }
                items(coin.team) { team ->
                    TeamItem(team)
                }
            }
        }
        if(state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if(state.isLoading) {
            CircularProgressIndicator(modifier = Modifier
                .align(Alignment.Center),
                color = MaterialTheme.colorScheme.tertiary
            )
        }

    }

}



@Composable
fun BasicInfoItem(coin : Coin) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(30.dp))
        ) {
            AsyncImage(
                model = coin.logo,
                placeholder = painterResource(id = R.drawable.placeholder),
                contentDescription = "crypto logo",
                modifier = Modifier
                    .size(120.dp)
                    .padding(20.dp)
            )
        }
        Column(
            modifier = Modifier.padding(start = 20.dp)
        ) {
            Text(
                text = "#${coin.rank}",
                color = MaterialTheme.colorScheme.surfaceVariant,
                fontFamily = fonts,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
            Text(
                text = "${coin.name} (${coin.symbol})",
                fontFamily = fonts,
                fontWeight = FontWeight.Medium,
                fontSize = 22.sp,
                color = MaterialTheme.colorScheme.tertiary,
                lineHeight = 28.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = if(coin.isActive) "active" else "inactive",
                    style = MaterialTheme.typography.labelSmall,
                    color = if(coin.isActive) Green else Red
                )
                Spacer(modifier = Modifier.width(20.dp))
                if(coin.openSource) {
                    Text(
                        text = "open source",
                        style = MaterialTheme.typography.labelSmall,
                        color = Orange
                    )
                }
            }
        }
    }
}

@Composable
fun CryptoTag(tag : Tag) {
    Box(
        modifier = Modifier
            .shadow(2.dp, RoundedCornerShape(100.dp))
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(100.dp),
            )
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        Text(
            text = tag.name,
            color = LightBlue,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
fun TeamItem(team : Team) {
    Column(
        Modifier.padding(horizontal = 40.dp)
    ) {
        Text(
            text = team.name,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.tertiary
        )
        Text(
            text = team.position,
            fontFamily = fonts,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.surfaceVariant
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colorScheme.outline, CircleShape)
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}