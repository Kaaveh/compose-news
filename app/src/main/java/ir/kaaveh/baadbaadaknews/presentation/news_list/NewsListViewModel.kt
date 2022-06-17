package ir.kaaveh.baadbaadaknews.presentation.news_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.kaaveh.baadbaadaknews.common.Resource
import ir.kaaveh.baadbaadaknews.domain.model.Article
import ir.kaaveh.baadbaadaknews.domain.usecase.AddFavoriteNewsUseCase
import ir.kaaveh.baadbaadaknews.domain.usecase.GetJsonNewsUseCase
import ir.kaaveh.baadbaadaknews.domain.usecase.RemoveFavoriteNewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val getJsonNewsUseCase: GetJsonNewsUseCase,
    private val addFavoriteNewsUseCase: AddFavoriteNewsUseCase,
    private val removeFavoriteNewsUseCase: RemoveFavoriteNewsUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(NewsListState())
    val state: State<NewsListState> = _state

    init {
        getNewsList()
    }

    private fun getNewsList() = getJsonNewsUseCase().onEach { result ->
        when (result) {
            is Resource.Loading -> {
                _state.value = NewsListState(isLoading = true)
            }
            is Resource.Success -> {
                _state.value = NewsListState(news = result.data ?: listOf())
            }
            is Resource.Error -> {
                _state.value = NewsListState(
                    error = result.exception?.localizedMessage ?: "An unexpected error occurred."
                )
            }
        }
    }.launchIn(viewModelScope)

    fun onFavoriteClick(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            if (!article.isFavorite)
                addFavoriteNewsUseCase(article)
            else
                removeFavoriteNewsUseCase(article)
        }
        val newsList = _state.value.news.toMutableList()
        newsList.find { it.title == article.title }?.isFavorite = !article.isFavorite
        _state.value = state.value.copy(news = newsList)
    }

}