package ir.kaaveh.baadbaadaknews.presentation.news_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentComposer
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import ir.kaaveh.baadbaadaknews.presentation.news_list.component.NewsListItem

@Destination(start = true)
@Composable
fun NewsListScreen(
    navigator: DestinationsNavigator,
    viewModel: NewsListViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(state.news) { article ->
                NewsListItem(
                    news = article,
                    onItemClick = {
                        // TODO: Navigate
                    },
                    onFavoriteClick = { viewModel.onFavoriteClick(article) }
                )
            }
        }

        if (state.error.isNotBlank())
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )

        if (state.isLoading)
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

    }

}