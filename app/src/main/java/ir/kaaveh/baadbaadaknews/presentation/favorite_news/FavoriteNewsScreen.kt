package ir.kaaveh.baadbaadaknews.presentation.favorite_news

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import ir.kaaveh.baadbaadaknews.presentation.destinations.NewsDetailScreenDestination
import ir.kaaveh.baadbaadaknews.presentation.news_list.NewsListViewModel
import ir.kaaveh.baadbaadaknews.presentation.news_list.component.NewsListItem

@Destination
@Composable
fun FavoriteNewsScreen(
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
                        navigator.navigate(
                            NewsDetailScreenDestination(article = article)
                        )
                    },
                    onFavoriteClick = {
                        viewModel.onFavoriteClick(article)
                    }
                )
            }
        }
    }

}