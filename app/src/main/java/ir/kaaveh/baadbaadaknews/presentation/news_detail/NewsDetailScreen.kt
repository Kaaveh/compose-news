package ir.kaaveh.baadbaadaknews.presentation.news_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
import com.ramcosta.composedestinations.annotation.Destination
import ir.kaaveh.baadbaadaknews.domain.model.Article

@Destination
@Composable
fun NewsDetailScreen(
    article: Article,
    viewModel: NewsDetailViewModel = hiltViewModel()
) {

    val webviewState = rememberWebViewState(article.url)
    viewModel.setFavoriteState(isFavorite = article.isFavorite)
    val favoriteState = viewModel.favoriteState.value

    Box(modifier = Modifier.fillMaxSize()) {
        WebView(
            state = webviewState,
            modifier = Modifier.fillMaxSize(),
            captureBackPresses = false,
        )

        FloatingActionButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            onClick = { viewModel.onFavoriteClick(article) },
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "",
                tint = when (favoriteState) {
                    true -> {
                        Color.Red
                    }
                    false -> {
                        Color.LightGray
                    }
                },
            )
        }
    }

}