package `in`.hypernation.cryptowiki.screens.home_screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import `in`.hypernation.cryptowiki.R
import `in`.hypernation.cryptowiki.screens.Screen
import `in`.hypernation.cryptowiki.screens.view_models.CoinListViewModel
import `in`.hypernation.cryptowiki.ui.theme.Grey
import `in`.hypernation.cryptowiki.ui.theme.fonts
import java.lang.Float.min

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: CoinListViewModel = hiltViewModel()
) {
    val lazyState = rememberLazyListState()
    val state = viewModel.state.value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 24.sp,
                    fontFamily = fonts,
                    fontWeight = FontWeight.SemiBold

                )
                SearchCard()

            }
            LazyColumn(
                state = lazyState,
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    Column(
                        modifier = Modifier
                            .padding(30.dp, 20.dp, 20.dp, 40.dp)
                            .graphicsLayer {
                                alpha = min(1f, 1 - (lazyState.firstVisibleItemScrollOffset / 300f))
                                translationY = -lazyState.firstVisibleItemScrollOffset * 0.1f
                            }
                    ) {
                        Text(
                            text = "here,",
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            fontSize = 24.sp,
                            fontFamily = fonts,
                            fontWeight = FontWeight.Normal,

                            )
                        Text(
                            text = stringResource(id = R.string.app_description),
                            color = MaterialTheme.colorScheme.tertiary,
                            fontSize = 28.sp,
                            fontFamily = fonts,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.fillMaxWidth(),
                            lineHeight = 36.sp
                        )
                    }
                }
                items(state.data) { coin ->
                    CryptoListItem(coin) {
                        navController.navigate(Screen.DetailScreen.route + "/${coin.id}")
                    }
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
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center),
                color = MaterialTheme.colorScheme.tertiary,
            )
        }

    }
}

@Composable
fun SearchCard() {
    Row(
        modifier = Modifier
            .shadow(1.dp, RoundedCornerShape(100.dp))
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(100.dp))
            .padding(vertical = 12.dp, horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Image(
            imageVector = Icons.Rounded.Search,
            contentDescription = "search",
            modifier = Modifier
                .size(24.dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.tertiary)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = "Search",
            color = MaterialTheme.colorScheme.tertiary,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(bottom = 1.dp)
        )
    }
}

