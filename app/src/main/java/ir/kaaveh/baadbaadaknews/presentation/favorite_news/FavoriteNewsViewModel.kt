package ir.kaaveh.baadbaadaknews.presentation.favorite_news

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.kaaveh.baadbaadaknews.domain.model.Article
import ir.kaaveh.baadbaadaknews.domain.usecase.AddFavoriteNewsUseCase
import ir.kaaveh.baadbaadaknews.domain.usecase.GetFavoriteNewsUseCase
import ir.kaaveh.baadbaadaknews.domain.usecase.RemoveFavoriteNewsUseCase
import ir.kaaveh.baadbaadaknews.presentation.news_list.NewsListState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteNewsViewModel @Inject constructor(
    private val addFavoriteNewsUseCase: AddFavoriteNewsUseCase,
    private val removeFavoriteNewsUseCase: RemoveFavoriteNewsUseCase,
    private val getFavoriteNewsUseCase: GetFavoriteNewsUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(NewsListState())
    val state: State<NewsListState> = _state

    init {
        getFavoriteNews()
    }

    private fun getFavoriteNews() = getFavoriteNewsUseCase().onEach {
        _state.value = _state.value.copy(news = it)
    }.launchIn(viewModelScope)

    fun onFavoriteClick(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            if (!article.isFavorite)
                addFavoriteNewsUseCase(article)
            else
                removeFavoriteNewsUseCase(article)
        }
    }

}