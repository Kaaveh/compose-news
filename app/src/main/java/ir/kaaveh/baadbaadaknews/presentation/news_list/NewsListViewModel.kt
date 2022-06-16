package ir.kaaveh.baadbaadaknews.presentation.news_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.kaaveh.baadbaadaknews.common.Resource
import ir.kaaveh.baadbaadaknews.domain.usecase.GetJsonNewsUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class NewsListViewModel @Inject constructor(
    private val getJsonNewsUseCase: GetJsonNewsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(NewsListState())
    val state: State<NewsListState> = _state

    init {
        getNewsList()
    }

    private fun getNewsList() = getJsonNewsUseCase().onEach { result ->
        when(result){
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

}