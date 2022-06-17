package ir.kaaveh.baadbaadaknews.presentation.news_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.kaaveh.baadbaadaknews.domain.model.Article
import ir.kaaveh.baadbaadaknews.domain.usecase.AddFavoriteNewsUseCase
import ir.kaaveh.baadbaadaknews.domain.usecase.RemoveFavoriteNewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(
    private val addFavoriteNewsUseCase: AddFavoriteNewsUseCase,
    private val removeFavoriteNewsUseCase: RemoveFavoriteNewsUseCase,
) : ViewModel() {

    fun onFavoriteClick(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            if (!article.isFavorite) {
                addFavoriteNewsUseCase(article)
            } else {
                removeFavoriteNewsUseCase(article)
            }
        }
    }

}