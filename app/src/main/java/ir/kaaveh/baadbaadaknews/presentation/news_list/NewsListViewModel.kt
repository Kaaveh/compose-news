package ir.kaaveh.baadbaadaknews.presentation.news_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.kaaveh.baadbaadaknews.common.Resource
import ir.kaaveh.baadbaadaknews.domain.model.Article
import ir.kaaveh.baadbaadaknews.domain.usecase.AddFavoriteNewsUseCase
import ir.kaaveh.baadbaadaknews.domain.usecase.GetFavoriteNewsUseCase
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
    private val getFavoriteNewsUseCase: GetFavoriteNewsUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(NewsListState())
    val state: State<NewsListState> = _state

    init {
        getNewsList()
        getFavoriteNews()
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

    private fun getFavoriteNews() = getFavoriteNewsUseCase().onEach { favoriteList ->
        val updatedList = _state.value.news.map { article ->
            val temp = favoriteList.find { it.title == article.title }
            temp?.copy(isFavorite = true) ?: article.copy(isFavorite = false)
        }
        _state.value = _state.value.copy(news = updatedList)
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